package com.eduardonetto.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardonetto.main.components.JsonFetcher;
import com.eduardonetto.main.components.data.JsonData;
import com.eduardonetto.main.controllers.dto.TransactionDTO;
import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.entities.enums.UserType;
import com.eduardonetto.main.services.exceptions.TransactionException;

@Service
public class TransactionService {

	@Autowired
	private UserService userService;

	@Autowired
	private JsonFetcher fetcher;

	public void transfer(TransactionDTO dto) {

		User payer = userService.findById(dto.payerId());
		User receiver = userService.findById(dto.receiverId());

		if (dto.amount() <= 0) {
			throw new TransactionException("Amount must be more than 0.");
		}

		if (payer.getType().equals(UserType.MERCHANT)) {
			throw new TransactionException("Merchant users cannot send money. Only receive.");
		}

		if (payer.getBalance() == 0 || (payer.getBalance() - dto.amount()) < 0) {
			throw new TransactionException("Invalid balance for send this money amount.");
		}

		JsonData data = fetcher.fetchJsonData("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6");

		if (!data.getContent().equals("Autorizado")) {
			throw new TransactionException("Transaction not authorized.");
		}

		payer.setBalance(payer.getBalance() - dto.amount());
		receiver.setBalance(receiver.getBalance() + dto.amount());

		userService.save(payer);
		userService.save(receiver);

	}

}

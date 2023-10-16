package com.eduardonetto.main.services;

import java.util.Random;

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

	private String authorized = "https://run.mocky.io/v3/f81e50db-aaaf-4dac-a5e5-844c5db30dab";
	private String rejected = "https://run.mocky.io/v3/265c27e0-b21f-4908-8b8c-7b0165ba30f1";
	private Random random = new Random();

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

		int number = random.nextInt(1, 3);
		JsonData data;

		if (number == 1) {
			data = fetcher.fetchJsonData(authorized);
		} else {
			data = fetcher.fetchJsonData(rejected);
		}

		if (!data.getContent().equals("Authorized")) {
			throw new TransactionException("Transaction not authorized.");
		}

		payer.setBalance(payer.getBalance() - dto.amount());
		receiver.setBalance(receiver.getBalance() + dto.amount());

		userService.save(payer);
		userService.save(receiver);
	}

}

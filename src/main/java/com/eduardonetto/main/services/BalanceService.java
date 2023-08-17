package com.eduardonetto.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardonetto.main.components.JsonFetcher;
import com.eduardonetto.main.components.data.JsonData;
import com.eduardonetto.main.controllers.dto.BalanceDTO;
import com.eduardonetto.main.controllers.dto.UserDTO;
import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.services.exceptions.BalanceException;

@Service
public class BalanceService {

	@Autowired
	private UserService userService;

	@Autowired
	private JsonFetcher fetcher;

	public BalanceDTO getBalance(Long id) {
		JsonData data = fetcher.fetchJsonData("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6");

		if (!data.getContent().equals("Autorizado")) {
			throw new BalanceException("You don't have access to this function.");
		}

		User user = userService.findById(id);
		return new BalanceDTO(user.getBalance());
	}

	public UserDTO deposit(Long id, BalanceDTO balanceDto) {
		JsonData data = fetcher.fetchJsonData("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6");

		if (!data.getContent().equals("Autorizado")) {
			throw new BalanceException("You don't have access to this function.");
		}

		if (balanceDto.amount() <= 0) {
			throw new BalanceException("Amount must be more than 0.");
		}

		User user = userService.findById(id);
		user.addBalance(balanceDto.amount());
		user = userService.save(user);
		
		return userService.toDto(user);
	}

}

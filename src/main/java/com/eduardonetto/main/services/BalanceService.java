package com.eduardonetto.main.services;

import java.util.Random;

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

	private String authorized = "https://run.mocky.io/v3/f81e50db-aaaf-4dac-a5e5-844c5db30dab";
	private String rejected = "https://run.mocky.io/v3/265c27e0-b21f-4908-8b8c-7b0165ba30f1";
	private Random random = new Random();

	public BalanceDTO getBalance(Long id) {
		int number = random.nextInt(1, 3);
		JsonData data;

		if (number == 1) {
			data = fetcher.fetchJsonData(authorized);
		} else {
			data = fetcher.fetchJsonData(rejected);
		}

		if (!data.getContent().equals("Authorized")) {
			throw new BalanceException("You don't have access to this function.");
		}

		User user = userService.findById(id);
		return new BalanceDTO(user.getBalance());
	}

	public UserDTO deposit(Long id, BalanceDTO balanceDto) {
		int number = random.nextInt(1, 3);
		JsonData data;

		if (number == 1) {
			data = fetcher.fetchJsonData(authorized);
		} else {
			data = fetcher.fetchJsonData(rejected);
		}

		if (!data.getContent().equals("Authorized")) {
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

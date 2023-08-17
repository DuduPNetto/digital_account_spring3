package com.eduardonetto.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardonetto.main.controllers.dto.BalanceDTO;
import com.eduardonetto.main.controllers.dto.UserDTO;
import com.eduardonetto.main.services.BalanceService;

@RestController
@RequestMapping("/balance/")
public class BalanceController {

	@Autowired
	private BalanceService service;

	@GetMapping("/{id}")
	public ResponseEntity<BalanceDTO> getBalance(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.getBalance(id));
	}

	@PostMapping("/deposit/{id}")
	public ResponseEntity<UserDTO> deposit(@PathVariable Long id, @RequestBody BalanceDTO balanceDto) {
		return ResponseEntity.ok().body(service.deposit(id, balanceDto));
	}

}

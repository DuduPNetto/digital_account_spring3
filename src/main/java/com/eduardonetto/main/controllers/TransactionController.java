package com.eduardonetto.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardonetto.main.controllers.dto.TransactionDTO;
import com.eduardonetto.main.services.TransactionService;

@RestController
@RequestMapping("/transaction/")
public class TransactionController {

	@Autowired
	private TransactionService service;

	@PostMapping
	public ResponseEntity<Void> transfer(@RequestBody TransactionDTO transaction) {
		service.transfer(transaction);
		return ResponseEntity.ok().build();
	}

}

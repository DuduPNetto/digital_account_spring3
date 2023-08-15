package com.eduardonetto.main.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardonetto.main.controllers.dto.UserDTO;
import com.eduardonetto.main.services.UserService;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> list = service.findAll().stream().map(x -> service.toDto(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

}

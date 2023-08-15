package com.eduardonetto.main.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eduardonetto.main.controllers.dto.InsertDTO;
import com.eduardonetto.main.controllers.dto.UserDTO;
import com.eduardonetto.main.entities.User;
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

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		User user = service.findById(id);
		return ResponseEntity.ok().body(service.toDto(user));
	}

	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody InsertDTO obj) {
		User user = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(service.toDto(user));
	}

}

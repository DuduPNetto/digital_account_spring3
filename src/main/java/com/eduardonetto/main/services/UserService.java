package com.eduardonetto.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardonetto.main.controllers.dto.UserDTO;
import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.repositories.UserRepository;
import com.eduardonetto.main.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found with id: " + id));
	}

	public UserDTO toDto(User user) {
		return new UserDTO(user.getId(), user.getName(), user.getCpf(), user.getEmail(), user.getPassword(),
				user.getType(), user.getBalance());
	}

}

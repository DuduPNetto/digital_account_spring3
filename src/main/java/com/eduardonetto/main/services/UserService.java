package com.eduardonetto.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.eduardonetto.main.controllers.dto.InsertDTO;
import com.eduardonetto.main.controllers.dto.UserDTO;
import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.entities.enums.UserType;
import com.eduardonetto.main.repositories.UserRepository;
import com.eduardonetto.main.services.exceptions.DatabaseException;
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

	public User insert(InsertDTO obj) {
		User user = new User(null, obj.name(), obj.cpf(), obj.email(), obj.password(),
				UserType.valueOf(obj.type().toUpperCase()), 0.0);

		try {
			user = repository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Email or CPF/CNPJ has already been registered.");
		}

		return user;
	}

	public User update(Long id, InsertDTO obj) {
		User user = findById(id);

		if (obj.name() != null)
			user.setName(obj.name());
		if (obj.cpf() != null)
			user.setCpf(obj.cpf());
		if (obj.email() != null)
			user.setEmail(obj.email());
		if (obj.password() != null)
			user.setPassword(obj.password());
		if (obj.type() != null)
			user.setType(UserType.valueOf(obj.type().toUpperCase()));

		try {
			user = repository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Email or CPF/CNPJ has already been registered.");
		}

		return user;
	}

	public void delete(Long id) {
		User user = findById(id);
		repository.delete(user);
	}

}

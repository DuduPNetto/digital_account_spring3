package com.eduardonetto.main;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.eduardonetto.main.controllers.dto.UserDTO;
import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.entities.enums.UserType;
import com.eduardonetto.main.repositories.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("dev")
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;

	@Autowired
	private EntityManager manager;

	@Test
	@Transactional
	@DisplayName("Create User Case 1")
	public void createUserCase1() {
		UserDTO dto = new UserDTO(null, "Name 1", "123.123.123-12", "email@email.com", "123", UserType.SIMPLE, 100.0);
		createUser(dto);
	}

	@Test
	@DisplayName("Find All Users")
	public void findAllUsers() {
		repository.findAll();
	}

	@Test
	@DisplayName("Find User By ID")
	public void findUserById() {
		Long id = 1l;
		Optional<User> obj = repository.findById(id);
		assertThat(obj.isPresent()).isTrue();
	}

	private User createUser(UserDTO dto) {
		User user = new User(dto);
		manager.persist(user);
		return user;
	}

}

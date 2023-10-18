package com.eduardonetto.main.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.entities.enums.UserType;
import com.eduardonetto.main.repositories.UserRepository;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		User u1 = new User(null, "Name 1", "123.123.123-11", "email@email.com", "123", UserType.SIMPLE, 100.0);
		User u2 = new User(null, "Name 2", "123.123.123-12", "email2@email.com", "123", UserType.MERCHANT, 1000.94);
		User u3 = new User(null, "Name 3", "123.123.123-13", "email3@email.com", "123", UserType.SIMPLE, 50.5);
		userRepository.saveAll(Arrays.asList(u1, u2, u3));

	}

}

package com.eduardonetto.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardonetto.main.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

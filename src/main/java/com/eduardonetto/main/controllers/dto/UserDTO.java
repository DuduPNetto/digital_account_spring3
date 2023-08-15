package com.eduardonetto.main.controllers.dto;

import com.eduardonetto.main.entities.enums.UserType;

public record UserDTO(Long id, String name, String cpf, String email, String password, UserType type, Double balance) {

}

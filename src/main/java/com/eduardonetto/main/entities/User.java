package com.eduardonetto.main.entities;

import java.io.Serializable;
import java.util.Objects;

import com.eduardonetto.main.controllers.dto.UserDTO;
import com.eduardonetto.main.entities.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 100)
	private String name;
	@Column(unique = true, nullable = false, length = 30)
	private String cpf;
	@Column(unique = true, nullable = false, length = 100)
	private String email;
	@Column(nullable = false, length = 100)
	private String password;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType type;
	@Column(nullable = false)
	private Double balance;

	public User() {
	}

	public User(Long id, String name, String cpf, String email, String password, UserType type, Double balance) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
		this.type = type;
		this.balance = balance;
	}

	public User(UserDTO dto) {
		this.id = dto.id();
		this.name = dto.name();
		this.cpf = dto.cpf();
		this.email = dto.email();
		this.password = dto.password();
		this.type = dto.type();
		this.balance = dto.balance();
	}

	public void addBalance(double value) {
		balance += value;
	}

	public void removeBalance(double value) {
		balance -= value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}

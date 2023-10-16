package com.eduardonetto.main.entities.enums;

public enum UserType {

	SIMPLE("SIMPLE"), MERCHANT("MERCHANT");

	private String value;

	private UserType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}

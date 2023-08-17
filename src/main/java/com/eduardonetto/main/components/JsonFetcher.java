package com.eduardonetto.main.components;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.eduardonetto.main.components.data.JsonData;
import com.eduardonetto.main.services.exceptions.DatabaseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonFetcher {

	private RestTemplate restTemplate;

	public JsonFetcher(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public JsonData fetchJsonData(String url) {
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(response.getBody());
			JsonData jsonData = new JsonData();
			jsonData.setContent(jsonNode.get("message").asText());
			return jsonData;
		} catch (IOException e) {
			throw new DatabaseException("Error while doing transaction.");
		}
	}

}

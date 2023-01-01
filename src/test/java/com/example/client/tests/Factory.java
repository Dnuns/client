package com.example.client.tests;

import java.time.Instant;

import com.example.client.dto.ClientDTO;
import com.example.client.entity.Client;

public class Factory {
	
	public static Client createClient() {
		Client client = new Client(1L, "Morgado Barros","19770111M002U", 2000.2,Instant.parse("2000-10-20T03:00:00Z"));
		return client;
	}
	
	public static ClientDTO createClientDTO() {
		Client client = createClient();
		return new ClientDTO(client);
	}
}

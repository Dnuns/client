package com.example.client.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.client.dto.ClientDTO;
import com.example.client.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;

	public List<ClientDTO> getAllClients() {
		List<ClientDTO> clients = new ArrayList<>();
		repository.findAll().forEach(client -> clients.add( new ClientDTO(client)));
		return clients;
	}
	
	
}

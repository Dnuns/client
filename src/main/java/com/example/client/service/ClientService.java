package com.example.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.client.dto.ClientDTO;
import com.example.client.entity.Client;
import com.example.client.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;

	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(client -> new ClientDTO(client));
	}
	
	
}

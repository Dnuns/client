package com.example.client.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.client.dto.ClientDTO;
import com.example.client.entity.Client;
import com.example.client.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(client -> new ClientDTO(client));
	}

	@Transactional
	public ClientDTO findById(Long id) {

		Client entity = repository.findById(id).get();
		return new ClientDTO(entity);
	}
}

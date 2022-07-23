package com.example.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.dto.ClientDTO;
import com.example.client.service.ClientService;

@RestController
@RequestMapping(value = "/")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@GetMapping
	public List<ClientDTO> allClients(){
		return service.getAllClients();
	}
}

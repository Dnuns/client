package com.example.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.dto.ClientDTO;
import com.example.client.service.ClientService;

@RestController
@RequestMapping(value = "/v1")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@GetMapping(value = "/clients")
	public ResponseEntity<Page<ClientDTO>> findAll(
			@RequestParam(value = "page", defaultValue ="0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue ="5") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue ="ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue ="id") String orderBy
			){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<ClientDTO> list = service.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(list);
	}
}

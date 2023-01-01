package com.example.client.repository;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.client.entity.Client;

@DataJpaTest
public class ClientRepositoryTests {
	
	@Autowired
	private ClientRepository repository;
	
	private long existingId;
	private long nonExistingId;
	private long countTotalClients;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalClients = 10L;
		
	}
	
	@Test
	public void findAllPagedShouldReturnANoNullClientPage() {
		PageRequest page = PageRequest.of(0, 3);
		Page<Client> clients = repository.findAll(page);
		
		Assertions.assertNotNull(clients);
		Assertions.assertEquals(clients.getNumberOfElements(), 3);
	}
	
	@Test
	public void findByIdShouldReturnANotEmptyOptionalWhenIdExists() {
		Optional<Client> client = repository.findById(existingId);
		
		Assertions.assertTrue(client.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnAnEmptyOptionalWhenIdDoesNotExist() {		
		Optional<Client> client = repository.findById(nonExistingId);
		
		Assertions.assertFalse(client.isPresent());		
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {	
		Client client = new Client();
		client.setName("Bruno Almada");
		client.setCni("19890212M002U");
		client.setAmount(2000.0);
		client.setBirthDate(Instant.parse("1989-10-01T10:00:00Z"));
		client = repository.save(client);
		
		Assertions.assertNotNull(client.getId());
		Assertions.assertEquals(countTotalClients + 1, client.getId());
	}
	
	
	@Test
	public void deletShouldDeleteObjectWhenIdExists() {
		repository.deleteById(existingId);

		Optional<Client> result = repository.findById(existingId);	
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {			
			repository.deleteById(nonExistingId);
		});
	}
}

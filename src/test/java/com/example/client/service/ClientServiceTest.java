package com.example.client.service;

import static org.mockito.Mockito.times;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.client.dto.ClientDTO;
import com.example.client.entity.Client;
import com.example.client.repository.ClientRepository;
import com.example.client.tests.Factory;

@ExtendWith(SpringExtension.class)
public class ClientServiceTest {
	
	@InjectMocks
	private ClientService service;
	
	@Mock
	private ClientRepository repository;
	
	
	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private Client client;
	ClientDTO clientDTO;
	private PageImpl<Client> page;	
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		dependentId = 3L;
		client = Factory.createClient();
		clientDTO = Factory.createClientDTO();
		page = new PageImpl<>(List.of(client));
		
		Mockito.when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
		
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(client);
		
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(client));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		Mockito.doNothing().when(repository).deleteById(existingId);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
	}
	
	@Test
	public void updateShouldThrowExceptionWhenIdDoesNotExist() {

		Assertions.assertThrows(NullPointerException.class, () -> {
			service.update(nonExistingId, clientDTO);
		});			
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		// assert
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			service.findById(nonExistingId);
		});

		Mockito.verify(repository, Mockito.times(1)).findById(nonExistingId);
	}
	
	@Test
	public void findByIdShouldRetournClientDTOWhenIdExists() {
		ClientDTO result = service.findById(existingId);
		
		// assert	
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void findAllPagedShouldReturnPage() {
		
		Pageable pageable = PageRequest.of(0, 12);
		
		Page<ClientDTO> result = service.findAllPaged((PageRequest) pageable);
		
		Assertions.assertNotNull(result);
		
		Mockito.verify(repository, times(1)).findAll(pageable);
	}
	
	
	@Test
	public void deleteShouldThrowNullPointerExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			service.delete(nonExistingId);
		});
		

		Mockito.verify(repository, times(1)).deleteById(nonExistingId);
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
		
		Mockito.verify(repository, times(1)).deleteById(existingId);
	}

}

package com.example.client.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Pattern;

import com.example.client.entity.Client;

public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Name should not be empty")
	private String name;
	
	@NotBlank(message = "CNI should not be empty")
	@Pattern(regexp = "\\d{8}[MF]\\d{3}[A-Z]")
	private String cni;
	
	@PositiveOrZero(message = "Amount should be a positive number")
	private Double amount;
	
	@Past(message = "BirthDate should be a date in the past")
	private Instant birthDate;
	
	public ClientDTO() {
	}

	public ClientDTO(Long id, String name, String cni, Double amount, Instant birthDate) {
		this.id = id;
		this.name = name;
		this.cni = cni;
		this.amount = amount;
		this.birthDate = birthDate;
	}
	
	public ClientDTO(Client entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.cni = entity.getCni();
		this.amount = entity.getAmount();
		this.birthDate = entity.getBirthDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCni() {
		return cni;
	}

	public void setCpf(String cni) {
		this.cni = cni;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}
}

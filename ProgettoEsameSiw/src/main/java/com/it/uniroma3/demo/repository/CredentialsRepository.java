package com.it.uniroma3.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.it.uniroma3.demo.model.Credentials;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
	
	public Optional<Credentials> findByUsername(String username);
	
}

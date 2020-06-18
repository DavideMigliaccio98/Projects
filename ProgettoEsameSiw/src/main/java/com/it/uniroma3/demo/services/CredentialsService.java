package com.it.uniroma3.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.it.uniroma3.demo.model.Credentials;
import com.it.uniroma3.demo.repository.CredentialsRepository;

@Service
public class CredentialsService {
	
	@Autowired 
	protected PasswordEncoder passwordEncoder;
	
	@Autowired 
	protected CredentialsRepository credentialsRepository;
	
	
	@Transactional 
	public Credentials getCredentials(Long id) {
		Optional<Credentials> result = this.credentialsRepository.findById(id);
		return result.orElse(null);
		
		
		
	}
	
	@Transactional 
	public Credentials getCredentials(String username) {
		Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
		return result.orElse(null);
	}
	
	@Transactional
	public Credentials saveCredentials(Credentials credentials) {
		credentials.setRole(Credentials.DEFAULT_ROLE);
		credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
		return this.credentialsRepository.save(credentials);
	}
	
	@Transactional 
	public List<Credentials> getAllCredentials() {
		 List<Credentials> result = new ArrayList<>();
		 Iterable<Credentials> i = this.credentialsRepository.findAll();
		 for(Credentials credentials : i)
			  result.add(credentials);
		 return result;
		 
	}
	
	@Transactional
	public void deleteCredentials(String username) {
		Optional <Credentials> result = this.credentialsRepository.findByUsername(username);
		this.credentialsRepository.delete(result.get());

		 
	
		
	}

}

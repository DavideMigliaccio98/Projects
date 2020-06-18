package com.it.uniroma3.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.uniroma3.demo.model.Project;
import com.it.uniroma3.demo.model.User;
import com.it.uniroma3.demo.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User getUser (Long id) {
		Optional<User> result = this.userRepository.findById(id);
		return result.orElse(null);
	}	
	
	@Transactional 
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}
	
	
	@Transactional 
	public List<User> findAllUser() {
	    Iterable<User> i= this.userRepository.findAll();
	    ArrayList<User> lista= new ArrayList<>();
	    for(User u : i) {
	    	lista.add(u);
	    }
	    return lista;
	}
    
	@Transactional
	public List<User> getMembers(Project project) {
	return project.getMembers();
	}
	
	
}

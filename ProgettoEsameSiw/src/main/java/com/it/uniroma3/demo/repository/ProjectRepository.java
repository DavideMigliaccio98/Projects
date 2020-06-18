package com.it.uniroma3.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.it.uniroma3.demo.model.Project;
import com.it.uniroma3.demo.model.User;



@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
	
	public List<Project> findByMembers(User member);
	
	 public List<Project> findByOwner(User owner);
	 
	 public Optional<Project> findByNome(String nome);

	 

}

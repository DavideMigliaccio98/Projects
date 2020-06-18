package com.it.uniroma3.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.it.uniroma3.demo.model.Project;
import com.it.uniroma3.demo.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	public List<User> findByVisibleProjects(Project project);
}

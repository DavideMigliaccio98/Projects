package com.it.uniroma3.demo.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.it.uniroma3.demo.model.Task;


@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {
	
	

	
}

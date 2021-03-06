package com.it.uniroma3.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.uniroma3.demo.model.Project;
import com.it.uniroma3.demo.model.User;
import com.it.uniroma3.demo.repository.ProjectRepository;


@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	
	@Transactional 
	public Project getProject (Long id) {
		Optional<Project> result = this.projectRepository.findById(id);
		return result.orElse(null);
	}
	
	@Transactional
	public Project saveProject(Project project) {
		return this.projectRepository.save(project);
	}

	@Transactional
	public void deleteProject(Project project) {
		this.projectRepository.delete(project);
	}
	
	@Transactional
	public Project shareProjectWithUser(Project project, User user) {
		project.addMember(user);
		return this.projectRepository.save(project);
	}

	@Transactional
	public List<Project> retrieveProjectsOwnedBy(User user) {
	return this.projectRepository.findByOwner(user);
	}
	
	@Transactional
	public void deleteProjectByName(String nome) {
	    Optional <Project> result = this.projectRepository.findByNome(nome);
		this.projectRepository.delete(result.get());
	}
	
	
}

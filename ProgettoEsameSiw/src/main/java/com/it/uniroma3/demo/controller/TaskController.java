package com.it.uniroma3.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.it.uniroma3.demo.controller.validation.TaskValidator;
import com.it.uniroma3.demo.model.Project;
import com.it.uniroma3.demo.model.Task;
import com.it.uniroma3.demo.model.User;
import com.it.uniroma3.demo.project.controller.session.SessionData;
import com.it.uniroma3.demo.services.ProjectService;
import com.it.uniroma3.demo.services.TaskService;

@Controller
public class TaskController {

	@Autowired
	TaskService taskService;

	@Autowired
	ProjectService projectService;

	@Autowired
	TaskValidator taskValidator;


	@Autowired
	SessionData sessionData;

	@RequestMapping(value = { "/projects/{projectId}/addTask" }, method = RequestMethod.GET) 
	public String createTaskForm(Model model, @PathVariable Long projectId) {
		User loggedUser = sessionData.getLoggedUser();
		Project currentProject = this.projectService.getProject(projectId);
		model.addAttribute("currentProject",currentProject.getId());
		model.addAttribute("loggedUser",loggedUser);
		model.addAttribute("taskForm", new Task());


		return "createTask";

	}
	@RequestMapping(value= {"/projects/{projectId}/addTask"}, method = RequestMethod.POST)
	public String createTask(@Valid @ModelAttribute ("taskForm") Task taskForm, 
			BindingResult taskBindingResult, @PathVariable Long projectId, 
			Model model) {
		User loggedUser = sessionData.getLoggedUser();
		this.taskValidator.validate(taskForm, taskBindingResult);
		if(!taskBindingResult.hasErrors()) {
			Project currentProject = this.projectService.getProject(projectId);
			currentProject.addTask(taskForm);
			model.addAttribute("currentProject",currentProject.getId());
			return"redirect:/projects/" + projectId.toString() ;
		}

		model.addAttribute("loggedUser",loggedUser);
		return "createTask";

	}
}




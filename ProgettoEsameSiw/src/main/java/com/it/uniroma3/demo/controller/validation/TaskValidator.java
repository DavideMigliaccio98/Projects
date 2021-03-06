package com.it.uniroma3.demo.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.it.uniroma3.demo.model.Task;
import com.it.uniroma3.demo.services.TaskService;
@Component
public class TaskValidator implements Validator{
	final Integer MAX_NAME_LENGTH = 100;
	final Integer MIN_NAME_LENGTH = 2;
	final Integer MAX_DESCRIPTION_LENGTH = 1000;


	@Autowired
	TaskService taskService;

	@Override
	public void validate(Object o, Errors errors) {
		Task task = (Task) o;
		String nome = task.getNome().trim();
		String descrizione= task.getDescrizione().trim();

		if (nome.trim().isEmpty())
			errors.rejectValue("nome", "required");
		else if (nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
			errors.rejectValue("nome", "size");

		if(descrizione.length()>MAX_DESCRIPTION_LENGTH)
			errors.rejectValue("descrizione", "size");
	}



	@Override
	public boolean supports(Class<?> clazz) {
		return Task.class.equals(clazz);
	}



}

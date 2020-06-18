package com.it.uniroma3.demo.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.it.uniroma3.demo.controller.validation.CredentialsValidator;
import com.it.uniroma3.demo.controller.validation.UserValidator;
import com.it.uniroma3.demo.model.Credentials;
import com.it.uniroma3.demo.model.User;
import com.it.uniroma3.demo.project.controller.session.SessionData;
import com.it.uniroma3.demo.services.CredentialsService;

@Controller
public class AuthenticationController {
	
	@Autowired 
	CredentialsService credentialsService;
	
	@Autowired
	UserValidator userValidator;
	
	@Autowired
	CredentialsValidator credentialsValidator;
	
	@Autowired
	SessionData sessionData;
	
	@RequestMapping(value = {"/users/register"}, method = RequestMethod.GET)
	public String showRegisterForm(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("credentialsForm",new Credentials());
		
		return "registerUser";
	}
	
	@RequestMapping(value= {"/users/register"}, method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute ("userForm") User user, 
             BindingResult userBindingResult,
             @Valid @ModelAttribute("credentialsForm") Credentials credentials,
			BindingResult credentialsBindingResult,
			Model model) {
		
		this.userValidator.validate(user, userBindingResult);
		this.credentialsValidator.validate(credentials, credentialsBindingResult);
		
		if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
			
			credentials.setUser(user);
			credentialsService.saveCredentials(credentials);
			return "registrationSuccessfull";
		}
		return "registerUser";
	}

}

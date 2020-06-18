package com.it.uniroma3.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.it.uniroma3.demo.controller.validation.UserValidator;
import com.it.uniroma3.demo.model.Credentials;
import com.it.uniroma3.demo.model.User;
import com.it.uniroma3.demo.project.controller.session.SessionData;
import com.it.uniroma3.demo.repository.UserRepository;
import com.it.uniroma3.demo.services.CredentialsService;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserValidator userValidator;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	SessionData sessionData;

	@Autowired
	CredentialsService credentialsService;


	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		User loggedUser = sessionData.getLoggedUser();
		model.addAttribute("loggedUser", loggedUser);
		return "home";
	}


	@RequestMapping(value = { "/users/me" }, method = RequestMethod.GET)
	public String me(Model model) {
		User loggedUser = sessionData.getLoggedUser();
		Credentials credentials = sessionData.getLoggedCredentials();
		System.out.println(credentials.getPassword());
		model.addAttribute("loggedUser",loggedUser);
		model.addAttribute("credentials", credentials);

		return "userProfile";
	}


	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public String admin(Model model) {
		User loggedUser = sessionData.getLoggedUser();
		model.addAttribute("user", loggedUser);
		return "admin";
	}

	@RequestMapping(value = { "/admin/users" }, method = RequestMethod.GET)
	public String usersList(Model model) {
		User loggedUser = sessionData.getLoggedUser();
		List<Credentials> allCredentials = this.credentialsService.getAllCredentials();
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("credentialsList",allCredentials);
		return "allUsers";
	}


	@RequestMapping(value = { "/admin/users/{username}/delete" }, method = RequestMethod.POST)
	public String removeUser(Model model, @PathVariable String username) {
		this.credentialsService.deleteCredentials(username);
		return "redirect:/admin/users";




	}
}




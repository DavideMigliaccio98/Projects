package com.it.uniroma3.demo.project.controller.session;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.it.uniroma3.demo.model.Credentials;
import com.it.uniroma3.demo.model.User;
import com.it.uniroma3.demo.repository.CredentialsRepository;


@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionData {

   
    private User user;

    private Credentials credentials;
    

    @Autowired
    private CredentialsRepository credentialsRepository;



   
    public Credentials getLoggedCredentials() {
        if (this.credentials == null)
            this.update();
        return this.credentials;
    }

   
    public User getLoggedUser() {
        if (this.user == null)
            this.update();
        return this.user;
    }

   
    private void update() {
        UserDetails loggedUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.credentials = this.credentialsRepository.findByUsername(loggedUserDetails.getUsername()).get(); 
        this.credentials.setPassword("[PROTECTED]");
        this.user = this.credentials.getUser();
    
    }
}
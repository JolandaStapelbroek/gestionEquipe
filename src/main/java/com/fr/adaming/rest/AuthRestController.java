package com.fr.adaming.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.LoginDto;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.IAuthService;

@RestController
@RequestMapping(path = "/auth")
public class AuthRestController {
	
	@Autowired
	private IAuthService service;
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public User LoginAuthenticator(@RequestBody LoginDto loginDto) {
		User u = service.login(loginDto.getEmail(), loginDto.getPwd());
		return u;
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public User register(@RequestBody User user) {
		User u = service.register(user);
		return u;
	}

}

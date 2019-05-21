package com.fr.adaming.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.User;
import com.fr.adaming.service.IUserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api")
public class UserRestController {

	@Autowired
	private IUserService service;
	
	//Pour différencier update et create : On utilise l'id
	//Se fait dans la couche service

	
	// CONVENTION JSON HTTP sur les méthodes CRUD
	// Avec create il faut utiliser la méthode HTTP POST
	
	
	//Met des notes dans le Swagger / Détails pour le front
	// A consulter sur http://localhost:8787/swagger-ui.html
	//Créer un Dto sans ID pour préciser qu'il ne doit jamais avoir d'Id dans swagger
	@ApiOperation(notes = "L'ID de l'utilisateur doit être null", value = "create")
	@RequestMapping(path = "/users", method = RequestMethod.POST)
	public void create(@RequestBody User user) {
		service.create(user);
	}

	// Avec readAll il faut utiliser la méthode HTTP GET
	
	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public List<User> readAll() {
		return service.readAll();
	}
	
	// Avec readById il faut utiliser la méthode HTTP GET + Attribut ID
	// Pas desoin de donner un nom à la Pathvariable si c'est le même que l'attribut que l'on cherche
	@RequestMapping(path = "/users/{idParam}", method = RequestMethod.GET)
	public User readById(@PathVariable(name = "idParam") Long id) {
		return service.readById(id);
	}

	// Avec update il faut utiliser la méthode HTTP PUT
	@ApiOperation(notes = "L'ID de l'utilisateur ne doit pas être null", value = "update")
	@RequestMapping(path = "/users", method = RequestMethod.PUT)
	public void update(@RequestBody User user) {
		service.update(user);
	}

	// Avec update il faut utiliser la méthode HTTP DELETE
	@RequestMapping(path = "/users", method = RequestMethod.DELETE)
	public void delete(@RequestBody User user) {
		service.delete(user.getId());
	}

}

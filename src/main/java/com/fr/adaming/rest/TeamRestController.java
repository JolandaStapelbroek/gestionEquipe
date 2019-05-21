package com.fr.adaming.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Team;
import com.fr.adaming.service.ITeamService;

@RestController
@RequestMapping (path = "api")
public class TeamRestController {
	
	@Autowired
	private ITeamService  service;
	
	@RequestMapping(path = "/teams", method = RequestMethod.POST)
	public void create(@RequestBody Team team) {
		service.create(team);
	}
	@RequestMapping(path = "/teams", method = RequestMethod.GET)
	public List<Team> readAll() {
		return service.readAll();
	}
	
	@RequestMapping(path = "/teams/{idParam}", method = RequestMethod.GET)
	public Team readById(@PathVariable(name="idParam")long id){
		return service.readById(id);
	}
	
	@RequestMapping(path = "/teams", method = RequestMethod.PUT)
	public void update(@RequestBody Team team) {
		service.update(team);
	}
	
	@RequestMapping(path = "/teams", method = RequestMethod.DELETE)
	public void delete(@RequestBody Team team) {
		service.delete(team.getId());
	}

}

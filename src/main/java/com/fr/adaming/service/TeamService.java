package com.fr.adaming.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.TeamDao;
import com.fr.adaming.entity.Team;

@Service
public class TeamService implements ITeamService {

	@Autowired
	private TeamDao dao;
	private Logger log = Logger.getLogger(UserService.class);

	@Override
	public Team create(Team team) {
//Souci sur cette page
		if (team != null && team.getId() != null || team != null && team.getId() == 0) {
			if (team.getNom() == null) {
				log.info("TEAM CREATE : TeamName not filled in");
			} else {
				try {
					dao.save(team);
					log.info("TEAM CREATE : team successfully saved");
					return team;
				} catch (Exception e) {
					log.error(e.getStackTrace());
				}
			}
		} else if (team == null) {
			throw new NullPointerException("TEAM IS NULL");
		} else {
			log.error("TEAM CREATE : ID from team inserted manually not authorized");
		}
		return null;
	}

	@Override
	public Team readById(Long id) {

		try {
			Optional<Team> team = null;
			team = dao.findById(id);

			if (team.isPresent()) {
				log.info("READBYID : Team found");
				return team.get();
			} else {
				log.info("READBYID : Team ID not found");
			}
		} catch (Exception e) {
			log.error(e.getStackTrace());
		}
		return null;

	}

	@Override
	public List<Team> readAll() {
		return dao.findAll();
	}

	@Override
	public Team update(Team team) {
		
		
		if (team.getId() == null || team.getId() == 0) {
			log.info("UPDATE : TEAM ID is null / 0");
		} else {

			if (dao.existsById(team.getId())) {

				try {
					dao.save(team);
					log.info("UPDATE : Team updated successfully");
					return team;
				} catch (Exception e) {
					log.error(e.getStackTrace());
				}

			} else {

				log.info("UPDATE : Team id not found");
			}

		}
		return null;

	}

	@Override
	public boolean delete(Long id) {
		try {

			dao.deleteById(id);
			log.info("TEAM DELETE : team deleted successfully");
			return true;
		} catch (Exception e) {
			if (e instanceof IllegalArgumentException) {
				log.info("TEAM DELETE : User Id not found");

			} else {
				log.info("USER DELETE : User not found");
				log.error(e.getStackTrace());
			}
		}
		return false;

	}

}

package com.fr.adaming.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.UserDao;
import com.fr.adaming.entity.User;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserDao dao;

	// Enregistrer des données dans le fichier log : Journalisation : BESOIN non
	// fonctionnel
	// Pour maintenance
	private Logger log = Logger.getLogger(UserService.class);

	// On peut implémenter des conditions pour différentier update et create
	// On ne peut create que si id == null (ne fait que l'insertion)
	// On ne peut update que si id != null et exist (que update)
	// Log4j : journalisation applicative

	@Override
	public User create(User user) {

		if (user != null && user.getId() == null || user != null && user.getId() == 0) {
			if (user.getEmail() == null) {
				log.info("USER CREATE : Email missing");
			} else if (dao.findByEmail(user.getEmail()) != null) {
				log.info("USER CREATE : Email already exist");
			} else if (user.getPwd() == null) {
				log.info("USER CREATE : password missing");
			} else {
				try {
					user = dao.save(user);
					log.info("USER CREATE : User successfully saved");
					return user;
				} catch (Exception e) {
					log.error(e.getStackTrace());
				}
			}
		} else if (user == null) {
			throw new NullPointerException("USER IS NULL");
		} else {
			log.error("USER CREATE : ID from user inserted manually not authorized");
		}

		return user = null;

	}

	@Override
	public User readById(Long id) {

		Optional<User> optUser = null;

		try {
			optUser = dao.findById(id);
// IsPresent vérifie que le User optionnel existe bien
			if (optUser.isPresent()) {
				log.info("READBYID : User found");
				return optUser.get();
			} else {
				log.info("READBYID : ID not found");
			}

		} catch (Exception e) {
			log.error("READBYID : ID null ");
		}
		return null;
	}

	@Override
	public List<User> readAll() {
		return dao.findAll();
	}

	@Override
	public User update(User user) {

		
		if (user.getId() == null || user.getId() == 0) {
			log.info("UPDATE : ID is null / 0");
		} else {

			if (dao.existsById(user.getId())) {

				try {
					dao.save(user);
					log.info("UPDATE : user updated successfully");
					return user;
				} catch (Exception e) {
					log.error(e.getStackTrace());
				}

			} else {

				log.info("UPDATE : User id not found");
			}

		}
		return null;
	}

	@Override
	public boolean delete(Long id) {

		try {

			dao.deleteById(id);
			log.info("USER DELETE : User deleted successfully");
			return true;
		} catch (Exception e) {
			if (e instanceof IllegalArgumentException) {
				log.info("USER DELETE : User Id not found");

			} else {
				log.info("USER DELETE : User not found");
				log.error(e.getStackTrace());
			}
		}
		return false;
	}



}

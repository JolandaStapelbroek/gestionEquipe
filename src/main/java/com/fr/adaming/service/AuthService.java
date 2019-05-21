package com.fr.adaming.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.UserDao;
import com.fr.adaming.entity.User;

@Service
public class AuthService implements IAuthService {

	@Autowired
	private UserDao dao;

	private Logger log = Logger.getLogger(UserService.class);

	@Override
	public User login(String email, String pwd) {

		if (email != null && pwd != null) {
			User user = dao.findByEmailAndPwd(email, pwd);

			if (user != null) {
				log.info("LOGIN : User found in database");
				return user;
			} else {
				log.info("LOGIN : No user match to filled in information");
			}

		} else if (pwd == null && email == null) {
			log.info("LOGIN : Required fields have not been completed");
		} else if (email == null && pwd != null) {
			log.info("LOGIN : No email filled in");
		} else if (pwd == null && email != null) {
			log.info("LOGIN : No password filled in");
		}

		return null;
	}

	@Override
	public User register(User user) {
		if (user != null && user.getId() == null || user != null && user.getId() == 0) {
			if (user.getEmail() == null) {
				log.info("User REGISTER : Email missing");
			} else if (dao.findByEmail(user.getEmail()) != null) {
				log.info("User REGISTER : Email already exist");
			} else if (user.getPwd() == null) {
				log.info("User REGISTER : password missing");
			} else if (user.getNom() == null) {
				log.info("User REGISTER : name missing");
			} else if (user.getPrenom() == null) {
				log.info("User REGISTER : firstname missing");
			} else {
				try {
					user = dao.save(user);
					log.info("USER REGISTER : User successfully saved");
					return user;
				} catch (Exception e) {
					log.error(e.getStackTrace());
				}
			}
		} else if (user == null) {
			throw new NullPointerException("USER IS NULL");
		} else {
			log.error("User REGISTER : ID from user inserted manually not authorized");
		}

		return user = null;

	}

}

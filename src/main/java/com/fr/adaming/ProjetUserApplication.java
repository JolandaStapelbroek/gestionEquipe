package com.fr.adaming;

//import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.fr.adaming.entity.Team;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.ITeamService;
import com.fr.adaming.service.IUserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ProjetUserApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ProjetUserApplication.class, args);

		Team t = new Team(1L, "Equipe1");
		context.getBean(ITeamService.class).create(t);

		User u = new User(1L, "nom1", "prenom1", "email1", "pwd1", t);
		context.getBean(IUserService.class).create(u);

//		Logger log = Logger.getLogger(ProjetUserApplication.class);
//		log.trace("Message de log TRACE");
//		log.debug("Message de log DEBUG");
//		log.info("Message de log INFO");
//		log.warn("Message de log WARN");
//		log.error("Message de log ERROR");
//		log.fatal("Message de log FATAL");
	}

}

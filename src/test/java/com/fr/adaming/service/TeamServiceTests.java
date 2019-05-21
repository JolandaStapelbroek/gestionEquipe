package com.fr.adaming.service;

//import static org.junit.Assert.*;

//import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.entity.Team;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamServiceTests {

	@Autowired
	private ITeamService service;
	private static Team result;

	@BeforeClass
	public static void beforeClassMethod() {
	}

	@AfterClass
	public static void afterClassMethod() {
	}

	@After
	public void afterMethod() {
		if (result != null && result.getId() != null) {
			service.delete(result.getId());
			System.out.println("RESULT DELETED");
		}

	}

	//REVOIR METHODE CREATE : CONDITIONS
	
//	@Test
//	public void a_testCreate() {
//		Team test = new Team(null, "Equipe1");
//		result = service.create(test);
//		assertNotNull(result);
//	}
//
//	@Test
//	public void a_testReadById() {
//		System.out.println("*************testReadById()*************");
//		Team test = new Team(null, "Equipe1");
//		result = service.create(test);
//		Team resultId = service.readById(test.getId());
//		assertNotNull(resultId);
//	}
//
//	@Test
//	public void a_testReadAll() {
//		Team test = new Team(null, "Equipe1");
//		result = service.create(test);
//		List<Team> teamList = service.readAll();
//		assertNotNull(teamList);
//	}
//
	@Test
	public void a_testUpdate() {
	
	}

	@Test
	public void a_testDelete() {
		
	}

}

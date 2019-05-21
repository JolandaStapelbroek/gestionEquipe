package com.fr.adaming.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.entity.User;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceTests {
	
	@Autowired
	private IAuthService service;	
	@Autowired
	private IUserService userService;
	private static User result;
	
	@Before
	public void beforeMethod(){
		
	}

	@BeforeClass
	public static void beforeClassMethod() {
	}
	
	@AfterClass
	public static void afterClassMethod() {
	}

	@After
	public void afterMethod() {
		if (result != null && result.getId() != null) {
			userService.delete(result.getId());
			System.out.println("RESULT DELETED");
		}

	}

	@Test
	public void a_testLogin() {
		System.out.println("*************testLogin()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		result = service.register(testUser);
		User loggedU = service.login(result.getEmail(), result.getPwd());
		assertNotNull(loggedU);
		
	}
	
	@Test
	public void login_WithWrongInfo() {
		System.out.println("*************login_WithWrongInfo()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		result = service.register(testUser);
		User loggedU = service.login("email2@gmail.com", "pwd1234");
		assertNull(loggedU);
		
	}
	
	@Test
	public void login_WithWrongEmail() {
		System.out.println("*************login_WithWrongEmail()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		result = service.register(testUser);
		User loggedU = service.login("email2@gmail.com", result.getPwd());
		assertNull(loggedU);
		
	}
	
	@Test
	public void login_WithWrongPwd() {
		System.out.println("*************login_WithWrongPwd()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		result =service.register(testUser);
		User loggedU = service.login(result.getEmail(), "pwd1234");
		assertNull(loggedU);
		
	}
	
	@Test
	public void login_WithNoInfo() {
		System.out.println("*************login_WithNoInfo()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		result = service.register(testUser);
		User loggedU = service.login(null, null);
		assertNull(loggedU);
		
	}
	
	@Test
	public void login_WithNoEmail() {
		System.out.println("*************login_WithNoEmail()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		result = service.register(testUser);
		User loggedU = service.login(null, result.getPwd());
		assertNull(loggedU);
		
	}
	
	@Test
	public void login_WithNoPwd() {
		System.out.println("*************login_WithNoPwd()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		result = service.register(testUser);
		User loggedU = service.login(result.getEmail(), null);
		assertNull(loggedU);
		
	}
	
	@Test
	public void login_WithWrongEmailAndNoPwd() {
		System.out.println("*************login_With_Wrong_Email_And_No_Pwd()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		result = service.register(testUser);
		User loggedU = service.login("email2@gmail.com", null);
		assertNull(loggedU);
		
	}
	
	@Test
	public void login_WithWrongPwdAndNoEmail() {
		System.out.println("*************login_With_Wrong_Pwd_And_No_Email()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		result = service.register(testUser);
		User loggedU = service.login(null, "pwd1234");
		assertNull(loggedU);
		
	}

	@Test
	public void a_testRegister() {
		System.out.println("*************testRegister()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);	
		result = service.register(testUser);
		assertNotNull(result);		
	}
	
	@Test
	public void register_withIdenticalEmail() {		
		System.out.println("*************create_withIdenticalEmail()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		service.register(testUser);
		User testUser2 = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		result = service.register(testUser2);
		assertNull(result);
		userService.delete(testUser.getId());
	}
	
	@Test
	public void register_withNoInfo() {		
		System.out.println("*************register_withNoInfo()*************");
		User testUser = new User(null, null, null, null, null, null);
		result = service.register(testUser);
		assertNull(result);
		
	}
	
	@Test
	public void register_withNoEmail() {		
		System.out.println("*************register_withNoEmail()*************");
		User testUser = new User(null, "nom", "prenom", null, "pwd12345", null);
		result = service.register(testUser);
		assertNull(result);
		
	}
	
	@Test
	public void register_withNoPwd() {		
		System.out.println("*************register_withNoPwd()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", null, null);
		result = service.register(testUser);
		assertNull(result);
		
	}
	
	@Test
	public void register_withNoName() {		
		System.out.println("*************register_withNoPwd()*************");
		User testUser = new User(null, null, "prenom", "email@gmail.com", "pwd12345", null);
		result = service.register(testUser);
		assertNull(result);
		
	}
	
	@Test
	public void register_withNoFirstName() {		
		System.out.println("*************register_withNoPwd()*************");
		User testUser = new User(null, "nom", null, "email@gmail.com", "pwd12345", null);
		result = service.register(testUser);
		assertNull(result);
		
	}
	
	@Test
	public void register_withInsertedId() {		
		System.out.println("*************register_withInsertedID()*************");
		User testUser = new User(5L, "nom", "prenom", "email@gmail.com", "pwdTest", null);
		result = service.register(testUser);
		assertNull(result);
		
	}
	
	

}

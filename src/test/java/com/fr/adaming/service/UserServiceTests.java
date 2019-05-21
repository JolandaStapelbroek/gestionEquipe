package com.fr.adaming.service;

import static org.junit.Assert.*;

import java.util.List;

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
public class UserServiceTests {

	// Pour lancer uniquement les tests :
	// Run as Junit test
	// Avec Maven :Run as Maven Test
	
	//On crée une classe "miroir" pour réaliser des tests
	//Il existe des tests similaires pour chaque couche mais c'est un autre métier
	//Pour les controllers on ne peut faire de tests avec J-unit puisqu'ils gèrent la relation avec l'extérieur
	//Sans les annotations les tests ne peuvent fonctionner
	
	//On appelle les méthodes de la couche Service pour les tester
	//les méthodes assert permettent de réaliser les tests
	//Attention aux types de retours des méthodes !
	
	
	//Test : enregistrer des objets qui vont générer des exceptions : L'application a t'elle su gérer cela ?
	//Test unitaire à faire avant de passer au front
	//Meilleure qualité de code : tester la robustesse de l'application

	// @Ignore : ignorer ce test pour le moment : Prend un paramètre optionnel : Si
	// on veut préciser pour quoi le test est ignoré
	// @After/ AfterClass, Before/BeforeClass Class : Appelle Avant/Après de passer
	// a n'importe quelle méthode de test
	// After et Before : avant toute méthode de test : initialise l'environnement.
	// Appele pour chaque test unitaire. Ex After ; supprimer cet objet de la BDD
	// @Rule : Pour dire que la classe va être détruite après.Plusieurs conditions :
	// Doit etre public, static, doit implémenter une interface

	@Autowired
	private IUserService service;

	private static User result; // mettre en static

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
		// Besoin de mettre des conditions : erreur si result est null dans une méthode (type delete)
		//Necessaire quand on appelle des méthodes dans des méthodes
		if (result != null && result.getId() != null) {
			service.delete(result.getId());
			System.out.println("RESULT DELETED");
		}

	}

	@Test
	public void a_testCreate() {
		System.out.println("*************testCreate()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		result = service.create(testUser);
//		testUser.setId(result.getId());
//		assertEquals("User ajouté", testUser, result); 
//		Si on veut faire le equals, il faut faire en sorte d'attribuer un id suite à la création
		assertNotNull(result);	
//Méthode assertNotNull : Si elle renvoie un objet, c'est que la méthode save a fonctionnée				
	}
	
	@Test
	public void create_withIdenticalEmail() {		
		System.out.println("*************create_withIdenticalEmail()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		service.create(testUser);
		User testUser2 = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		result = service.create(testUser2);
		assertNull(result);
		service.delete(testUser.getId());
//Méthode assertNull : On vérifie qu'aucun objet n'est créé		
	}
	
	@Test
	public void create_withNoInfo() {		
		System.out.println("*************create_withNoInfo()*************");
		User testUser = new User(null, null, null, null, null, null);
		result = service.create(testUser);
		assertNull(result);
		
	}
	
	
	@Test
	public void create_withNoEmail() {		
		System.out.println("*************create_withNoEmail()*************");
		User testUser = new User(null, "nom", "prenom", null, "pwd12345", null);
		result = service.create(testUser);
		assertNull(result);
		
	}
	
	@Test
	public void create_withNoPwd() {		
		System.out.println("*************create_withNoPwd()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", null, null);
		result = service.create(testUser);
		assertNull(result);
		
	}
	
	@Test
	public void create_withInsertedId() {		
		System.out.println("*************c_withInsertedID()*************");
		User testUser = new User(5L, "nom", "prenom", "email@gmail.com", "pwdTest", null);
		result = service.create(testUser);
		assertNull(result);
		
	}

	@Test
	public void a_testDelete() {
		System.out.println("*************testDelete()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		result = service.create(testUser);
		assertTrue(service.delete(result.getId()));
//Methode assertTrue : Si on supprime un objet, le retour de la méthode delete : true
		
	}
	
	@Test
	public void delete_ifIdIsZero() {
		System.out.println("*************delete_ifIdIsZero()*************");
		User testUser = new User(null, "nom3", "prenom3", "email3@gmail.com", "pwd12345", null);
		result = service.create(testUser);
		Long res = (long) 0; 
		assertFalse(service.delete(res));
//Méthode assertFalse : Si aucun objet n'est supprimé : retour de la méthode delete : false.		
	}
	
	@Test
	public void delete_ifIdIsNull() {
		System.out.println("*************delete_ifIdIsNull()*************");
		User testUser = new User(null, "nom3", "prenom3", "email3@gmail.com", "pwd12345", null);
		result = service.create(testUser);
		assertFalse(service.delete(null));
		
	}
	
	@Test
	public void delete_ifIdNotExist() {
		System.out.println("*************delete_ifIdNotExist()*************");
		User testUser = new User(null, "nom3", "prenom3", "email3@gmail.com", "pwd12345", null);
		result = service.create(testUser);
		Long res = (long) 99;
		assertFalse(service.delete(res));
		
	}

	@Test
	public void a_testReadById() {
		System.out.println("*************testReadById()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		result = service.create(testUser);
		User resultId = service.readById(testUser.getId());
		assertNotNull(resultId);
		
	}
	
	@Test
	public void read_ifIdIsZero() {
		System.out.println("*************read_ifIdIsZero()*************");
		User testUser = new User(null, "nom3", "prenom3", "email3@gmail.com", "pwd12345", null);
		result = service.create(testUser);	
		Long res = (long) 0;
		User resultId = service.readById(res);
		assertNull(resultId);
//Méthode assertNull : Aucun objet trouvé dans la liste
	}
	
	@Test
	public void read_ifIdIsNull() {
		System.out.println("*************read_ifIdIsNull()*************");
		User testUser = new User(null, "nom3", "prenom3", "email3@gmail.com", "pwd12345", null);
		result = service.create(testUser);	
		User resultId = service.readById(null);
		assertNull(resultId);
		
	}
	
	@Test
	public void read_ifIdNotExist() {
		System.out.println("*************read_ifIdNotExist()*************");
		User testUser = new User(null, "nom3", "prenom3", "email3@gmail.com", "pwd12345", null);
		result = service.create(testUser);	
		Long res = (long) 99;
		User resultId = service.readById(res);
		assertNull(resultId);
		
	}

	@Test
	public void a_testReadAll() {
		System.out.println("*************testReadAll()*************");
		User testUser = new User(null, "nom3", "prenom3", "email3@gmail.com", "pwd12345", null);
		result = service.create(testUser);
		List<User> resultList = service.readAll();
		assertFalse(resultList.isEmpty());
//La liste d'objets à été trouvée et contient des éléments		
	}
	
	@Test
	public void readAll_emptyList() {
		System.out.println("*************readAll_emptylist()*************");		
		List<User> resultList = service.readAll();
		assertTrue(resultList.isEmpty());
//La liste d'objets trouvée est vide		
	}

	@Test
	public void a_testUpdate() {
		System.out.println("*************testUpdate()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		service.create(testUser);
		User updateUser = new User(testUser.getId(), "nomtest", "prenom", "emailtest@gmail.com", "pwd12345", null);
		result = service.update(updateUser);
		assertEquals("Nom mis à jour", "nomtest" ,result.getNom());
		assertEquals("Email mis à jour", "emailtest@gmail.com" ,result.getEmail());
		assertEquals("Même User mis à jour", testUser.getId() ,result.getId());
		assertNotNull(result);
				
	}
	
	@Test
	public void update_WithNoId() {
		System.out.println("*************update_WithNoId()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		service.create(testUser);
		User updateUser = new User(null, "nomtest", "prenom", "emailtest@gmail.com", "pwd12345", null);
		result = service.update(updateUser);
		assertNull(result);
		service.delete(testUser.getId());
				
	}
	
	@Test
	public void update_WithIdIsZero() {
		System.out.println("*************update_WithIdIsZero()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		service.create(testUser);
		User updateUser = new User(0L, "nomtest", "prenom", "emailtest@gmail.com", "pwd12345", null);
		result = service.update(updateUser);
		assertNull(result);
		service.delete(testUser.getId());
				
	}
	
	
	@Test
	public void update_WithNotExistId() {
		System.out.println("*************update_WithIdNotExist()*************");
		User testUser = new User(null, "nom", "prenom", "email@gmail.com", "pwd12345", null);
		service.create(testUser);
		User updateUser = new User(99L, "nomtest", "prenom", "emailtest@gmail.com", "pwd12345", null);
		result = service.update(updateUser);
		assertNull(result);
		service.delete(testUser.getId());
				
	}

}

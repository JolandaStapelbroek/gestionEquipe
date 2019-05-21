package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.User;

//Doit avoir le même type que l'id de la classe User !
@Repository
public interface UserDao extends JpaRepository<User, Long>{
	
	public User findByEmailAndPwd(String email, String pwd);
	public User findByEmail(String email);
	

}

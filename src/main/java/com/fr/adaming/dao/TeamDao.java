package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Team;

@Repository
public interface TeamDao extends JpaRepository<Team, Long>{

}

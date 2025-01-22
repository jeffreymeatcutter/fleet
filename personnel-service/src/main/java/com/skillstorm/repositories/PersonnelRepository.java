package com.skillstorm.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.models.Personnel;

import jakarta.transaction.Transactional;

@Repository 
public interface PersonnelRepository extends CrudRepository<Personnel, Integer> {
	@Transactional
	@Query(value = "SELECT * FROM personnel WHERE squadron_id = ?1", nativeQuery = true)
	Iterable<Personnel> getAllInSquadron(int squadronId);
}

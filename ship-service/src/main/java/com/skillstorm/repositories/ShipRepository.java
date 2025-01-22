package com.skillstorm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.models.Ship;

@Repository
public interface ShipRepository extends CrudRepository<Ship, Integer>{
	Iterable<Ship> findBySquadronId(int squadronId);
}

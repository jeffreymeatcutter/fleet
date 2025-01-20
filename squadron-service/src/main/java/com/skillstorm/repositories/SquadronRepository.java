package com.skillstorm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.models.Squadron;

@Repository
public interface SquadronRepository extends CrudRepository<Squadron, Integer>{

}

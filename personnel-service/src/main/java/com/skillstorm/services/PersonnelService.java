package com.skillstorm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillstorm.models.Personnel;
import com.skillstorm.repositories.PersonnelRepository;

@Service
public class PersonnelService {
	
	private PersonnelRepository repo;

	public PersonnelService(PersonnelRepository repo) {
		this.repo = repo;
	}
	
	public Iterable<Personnel> findAll(){
		return repo.findAll();
	}
	
	

}

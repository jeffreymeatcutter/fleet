package com.skillstorm.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.skillstorm.models.Personnel;
import com.skillstorm.services.PersonnelService;



@RestController
@RequestMapping("/personnel")
public class PersonnelController {
	
	private PersonnelService service;
	
	public PersonnelController(PersonnelService service) {
		this.service = service;
	}
	
	@GetMapping("/test")
	public Iterable<Personnel> findAll(){
		return service.findAll();
	}
	

}

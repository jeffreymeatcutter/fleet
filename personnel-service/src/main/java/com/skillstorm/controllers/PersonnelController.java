package com.skillstorm.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.skillstorm.dtos.PersonnelDTO;
import com.skillstorm.models.Personnel;
import com.skillstorm.services.PersonnelService;



@RestController
@RequestMapping("/personnel")
public class PersonnelController {
	
	private PersonnelService service;
	
	public PersonnelController(PersonnelService service) {
		this.service = service;
	}
	
	@GetMapping()
	public ResponseEntity<Iterable<Personnel>> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{personnelId}")
	public ResponseEntity<Personnel> findById(@PathVariable int personnelId) {
		return service.findById(personnelId);
	}
	
	@GetMapping("/squadron/{squadId}")
	public ResponseEntity<Iterable<Personnel>> getAllInSquad(@PathVariable int squadId){
		return service.getAllInSquad(squadId);
	}
	
	@PostMapping
	public ResponseEntity<Personnel> addOne(@RequestBody PersonnelDTO personnelDTO){
		return service.updateOrCreate(-1, personnelDTO);
	}
	
	@PutMapping("/{personnelId}")
	public ResponseEntity<Personnel> updateOne(@PathVariable int personnelId, @RequestBody PersonnelDTO personnelDTO){
		return service.updateOrCreate(personnelId, personnelDTO);
	}

	@DeleteMapping("/{personnelId}")
	public ResponseEntity<Void> deleteOne(@PathVariable int personnelId){
		return service.deleteOne(personnelId);
	}
	
}

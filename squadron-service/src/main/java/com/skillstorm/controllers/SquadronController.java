package com.skillstorm.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.dtos.SquadronDTO;
import com.skillstorm.models.Ship;
import com.skillstorm.models.Squadron;
import com.skillstorm.services.SquadronService;

@RestController
@RequestMapping("/squadron")
public class SquadronController {
	private SquadronService service; 

	public SquadronController(SquadronService service) {
		super();
		this.service = service;
	}
	
    @GetMapping("/ships/{squadronId}")
    public ResponseEntity<Iterable<Ship>> getShipsBySquadron(@PathVariable int squadronId) {
    	Iterable<Ship> ships = service.getShipsBySquadron(squadronId);
        return ResponseEntity.ok(ships);
    }
	
	@GetMapping
	public ResponseEntity<Iterable<Squadron>> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Squadron> findById (@PathVariable int id) {
		return service.findById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Squadron> update(@PathVariable int id, @RequestBody() SquadronDTO squadronToUpdate){
		return service.update(id, squadronToUpdate);
	}
	
	@PostMapping
	public ResponseEntity<Squadron> create(@RequestBody() SquadronDTO squadronToCreate) {
		return service.create(squadronToCreate);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		return service.delete(id);
	}

}

package com.skillstorm.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.skillstorm.feignClients.PersonnelClient;
import com.skillstorm.models.Personnel;
import com.skillstorm.FeignClient.ShipFeignClient;
import com.skillstorm.dtos.SquadronDTO;
import com.skillstorm.models.Squadron;
import com.skillstorm.models.entities.Ship;
import com.skillstorm.repositories.SquadronRepository;


@Service
public class SquadronService {
	private SquadronRepository repo;
	private PersonnelClient personnelClient;
	private ShipFeignClient shipFeignClient;
	
	public SquadronService(ShipFeignClient shipFeignClient, PersonnelClient personnelClient, SquadronRepository repo) {
        this.shipFeignClient = shipFeignClient;
    		this.personnelClient = personnelClient;
        this.repo = repo;
    }
  
	//Get ships by squadId
	  public Iterable <Ship> getShipsBySquadron(int squadronId) {
	        return shipFeignClient.getShipsBySquadron(squadronId);
	    }
	
	public ResponseEntity<Iterable<Squadron>> findAll() {
		return ResponseEntity.status(HttpStatus.OK)
							 .body(repo.findAll());
	}
	public ResponseEntity<Squadron> findById(int id) {
		if(repo.existsById(id))
			return ResponseEntity.status(HttpStatus.OK)
					             .body(repo.findById(id).get());
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					             .body(null);
	}
	
	public ResponseEntity<Squadron> create (SquadronDTO squadronToCreate){
		return ResponseEntity.status(HttpStatus.CREATED)
					         .body(repo.save(new Squadron(0, squadronToCreate.getSquadronName(), squadronToCreate.getMaxCapacity())));
	}
	
	public ResponseEntity<Squadron> update(int id, SquadronDTO squadronDTO){
		
		if(repo.existsById(id)) {
			
			Squadron squadron = repo.findById(id).get();
			
			if(squadron.getMaxCapacity() != squadronDTO.getMaxCapacity()) {
				
				Personnel[] people = personnelClient.getPersonnel(id);
				
				if(squadronDTO.getMaxCapacity()< people.length || squadronDTO.getMaxCapacity()<0) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
				}
			}
			
			
			return ResponseEntity.status(HttpStatus.OK)
								 .body(repo.save(new Squadron(id, squadronDTO.getSquadronName(), squadronDTO.getMaxCapacity())));
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								 .body(null);
	}
	
	public ResponseEntity<Void> delete (int id) {
		
		//Deleting the personnel in the Squad by getting all and then changing the id to 1 (aka unassigned) one at a time
		Personnel[] people = personnelClient.getPersonnel(id);
		
        	for (Personnel person : people) {
        		person.setSquadronId(1);
        		personnelClient.changePersonnel(person.getPersonnelId(), person);
        	}
        
        //Deleting the ships in the Squad
		
		Iterable<Ship> ships = shipFeignClient.getShipsBySquadron(id);
		  
		for(Ship ship : ships) { 
			ship.setSquadronId(1);
			shipFeignClient.update(ship.getShipId(), ship); 
			}
		 
        
		repo.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				             .body(null);
	}
}

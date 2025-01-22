package com.skillstorm.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.FeignClient.ShipFeignClient;
import com.skillstorm.dtos.SquadronDTO;
import com.skillstorm.models.Ship;
import com.skillstorm.models.Squadron;
import com.skillstorm.repositories.SquadronRepository;


@Service
public class SquadronService {
	private SquadronRepository repo;
	private ShipFeignClient shipFeignClient;
	
	public SquadronService(ShipFeignClient shipFeignClient, SquadronRepository repo) {
        this.shipFeignClient = shipFeignClient;
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
		if(repo.existsById(id))
			return ResponseEntity.status(HttpStatus.OK)
								 .body(repo.save(new Squadron(id, squadronDTO.getSquadronName(), squadronDTO.getMaxCapacity())));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								 .body(null);
	}
	
	public ResponseEntity<Void> delete (int id) {
		repo.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				             .body(null);
	}
}

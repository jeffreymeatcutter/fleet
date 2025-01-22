package com.skillstorm.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.skillstorm.dtos.ShipDTO;
import com.skillstorm.models.Ship;
import com.skillstorm.repositories.ShipRepository;
  
@Service
public class ShipService {

    private final ShipRepository repo;


    public ShipService(ShipRepository repo) {
        this.repo = repo;
    }

    // Find all 
    public ResponseEntity<Iterable<Ship>> findAll() { 
        return ResponseEntity.status(HttpStatus.OK)
        		             .body(repo.findAll());
    }

    // Find by ID
    public ResponseEntity<Ship> findById(int shipId) {
    	if(repo.existsById(shipId))
			return ResponseEntity.status(HttpStatus.OK)
					             .body(repo.findById(shipId).get());
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					             .body(null);
    }

    // Create a new ship
    public ResponseEntity<Ship> create(ShipDTO shipDTO) {
    	return ResponseEntity.status(HttpStatus.CREATED)
		         .body(repo.save(new Ship(0, shipDTO.getShipName(), shipDTO.getShipType(), shipDTO.getSquadronId())));
    }

    // Update
    public ResponseEntity<Ship> update(int shipId, ShipDTO shipDTO) {
    	if(repo.existsById(shipId))
			return ResponseEntity.status(HttpStatus.OK)
								 .body(repo.save(new Ship(0, shipDTO.getShipName(), shipDTO.getShipType(), shipDTO.getSquadronId())));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								 .body(null);
    }

    // Delete
    public ResponseEntity<Void> delete(int shipId) {
        repo.deleteById(shipId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
	             .body(null);
    }

    
}

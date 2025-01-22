package com.skillstorm.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.skillstorm.dtos.SquadronDTO;
import com.skillstorm.models.Personnel;
import com.skillstorm.models.Ship;
import com.skillstorm.models.Squadron;
import com.skillstorm.repositories.SquadronRepository;


@Service
public class SquadronService {
	private SquadronRepository repo;

	public SquadronService(SquadronRepository repo) {
		super();
		this.repo = repo;
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
		
		//Deleting the personnel in the Squad
		
		RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<Personnel[]> responseEntity = rt.exchange("http://localhost:8082/personnel/squadron/" + id, HttpMethod.GET, entity, Personnel[].class);
        if(responseEntity.getStatusCode().is2xxSuccessful()) {
        	Personnel[] personnelList = responseEntity.getBody();
        	for (Personnel person : personnelList) {
        		person.setSquadronId(1);
        		
        		rt.exchange("http://localhost:8082/personnel/" + person.getPersonnelId(), HttpMethod.PUT, new HttpEntity<>(person, headers), Personnel.class);
        	}
        }
        
        //Deleting the ships in the Squad
        
        ResponseEntity<Ship[]> shipResponseEntity = rt.exchange("http://localhost:8082/ship/squadron/" + id, HttpMethod.GET, entity, Ship[].class);
        if(shipResponseEntity.getStatusCode().is2xxSuccessful()) {
        	Ship[] shipList = shipResponseEntity.getBody();
        	for(Ship ship : shipList) {
        		ship.setSquadronId(1);
        		
        		rt.exchange("http://localhost:8082/ship/" + ship.getShipId(), HttpMethod.PUT, new HttpEntity<>(ship, headers), Ship.class);
        	}
        }
        
		repo.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				             .body(null);
	}
}

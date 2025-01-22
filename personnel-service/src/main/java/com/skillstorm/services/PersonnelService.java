package com.skillstorm.services;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.hibernate.engine.spi.SelfDirtinessTracker;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.skillstorm.dtos.PersonnelDTO;
import com.skillstorm.models.Personnel;
import com.skillstorm.models.Squadron;
import com.skillstorm.repositories.PersonnelRepository;

@Service
public class PersonnelService {
	
	private PersonnelRepository repo;

	public PersonnelService(PersonnelRepository repo) {
		this.repo = repo;
	}
	
	public ResponseEntity<Iterable<Personnel>> findAll(){
		return ResponseEntity.status(HttpStatus.OK)
							 .body(repo.findAll());
	}
	
	public ResponseEntity<Personnel> findById(int personnelId){
		if(repo.existsById(personnelId)) {
			return ResponseEntity.status(HttpStatus.OK)
							 .body(repo.findById(personnelId).get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	public ResponseEntity<Iterable<Personnel>> getAllInSquad(int squadId){
		return ResponseEntity.status(HttpStatus.OK).body(repo.getAllInSquadron(squadId));
	}
	public ResponseEntity<Personnel> updateOrCreate(int personnelId, PersonnelDTO personnelDTO){

		//checks if the person is changing squads, and if so checking squad limits
		Optional<Personnel> personOptional = repo.findById(personnelId);
		Personnel person = personOptional.get();
		
		if(person.getSquadronId() != personnelDTO.getSquadronId()) {
			
			//Setting up to contact squadron to get maxCapacity
			RestTemplate rt = new RestTemplate();
	        HttpHeaders headers = new HttpHeaders();
	        HttpEntity<Object> entity = new HttpEntity<>(headers);
	        ResponseEntity<Squadron> responseEntity = rt.exchange("http://localhost:8667/squadron/" + personnelDTO.getSquadronId(), HttpMethod.GET, entity, Squadron.class);
	        Squadron squad = responseEntity.getBody();
	        
	        // there's probably a less sketchy way to do this... but I need the amount of people in the squad so...
	        ResponseEntity<Iterable<Personnel>> peeps = this.getAllInSquad(squad.getSquadronId());
	        ArrayList<Personnel> people = new ArrayList<Personnel>((Collection<? extends Personnel>) peeps.getBody());
	        
	        //if the squad is already full (or over full) it rejects the request
	        if(squad.getMaxCapacity()<= people.size()) {
	    
	        	
	        	return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	        }
		}
		
		
		//runs update if the person exists
		
		if(repo.existsById(personnelId)){
				
			return ResponseEntity.status(HttpStatus.OK)
								 .body(repo.save(new Personnel(personnelId,
										 						personnelDTO.getPersonnelName(),
										 						personnelDTO.isCommander(),
										 						personnelDTO.getSquadronId()
										 						)));
			}
		//Checking if this is coming in through the create route in the controller which auto gives an ID of -1
		else if (personnelId == -1) {
				return ResponseEntity.status(HttpStatus.CREATED)
						 .body(repo.save(new Personnel(0,
								 						personnelDTO.getPersonnelName(),
								 						personnelDTO.isCommander(),
								 						personnelDTO.getSquadronId()
								 						)));
			}
		//if not -1 (so from the create route) or an existing id it returns an error
		else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							 .body(null);
				}
		}
		
	public ResponseEntity<Void> deleteOne(int personnelId) {
		repo.deleteById(personnelId);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(null);
	}
	
	

}

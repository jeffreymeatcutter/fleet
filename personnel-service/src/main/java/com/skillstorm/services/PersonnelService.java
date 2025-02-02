package com.skillstorm.services;


import java.util.ArrayList;
import java.util.Collection;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.skillstorm.dtos.PersonnelDTO;
import com.skillstorm.feignClients.SquadronFeignClient;
import com.skillstorm.models.Personnel;
import com.skillstorm.models.Squadron;
import com.skillstorm.repositories.PersonnelRepository;

@Service
public class PersonnelService {
	
	private PersonnelRepository repo;
	
	private SquadronFeignClient squadClient;
	

	//constructor for bean injection
	public PersonnelService(SquadronFeignClient squadClient, PersonnelRepository repo) {
		super();
		this.squadClient = squadClient;
		this.repo = repo;
	}
	
	
	
	
	
	
	//Get all Personnel
	public ResponseEntity<Iterable<Personnel>> findAll(){
		return ResponseEntity.status(HttpStatus.OK)
							 .body(repo.findAll());
	}
	
	
	//Get one by ID (if it can't be found an error is returned)
	public ResponseEntity<Personnel> findById(int personnelId){
		if(repo.existsById(personnelId)) {
			return ResponseEntity.status(HttpStatus.OK)
							 .body(repo.findById(personnelId).get());
		}
		//if the person doesn't exist nothing is done
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	//For squad service (delete method) and this services update method 
	public ResponseEntity<Iterable<Personnel>> getAllInSquad(int squadId){
		return ResponseEntity.status(HttpStatus.OK).body(repo.getAllInSquadron(squadId));
	}
	
	
	
	//update and create (runs checks for which type)
	public ResponseEntity<Personnel> updateOrCreate(int personnelId, PersonnelDTO personnelDTO){
		
		
		//runs update if the person exists
		if(repo.existsById(personnelId)){
			
			//checks if the person is changing squads, and if so checking squad limits
			Personnel person = repo.findById(personnelId).get();
			
			if(person.getSquadronId() != personnelDTO.getSquadronId()) {
				
				//Setting up to contact squadron to get maxCapacity
				Squadron squad = squadClient.getSquadron(personnelDTO.getSquadronId());
		        
		        // there's probably a less sketchy way to do this... but I need the amount of people in the squad so...
		        ResponseEntity<Iterable<Personnel>> peeps = this.getAllInSquad(squad.getSquadronId());
		        ArrayList<Personnel> people = new ArrayList<Personnel>((Collection<? extends Personnel>) peeps.getBody());
		        
		        //if the squad is already full (or over full) it rejects the request
		        if(squad.getMaxCapacity()<= people.size()) {
		        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		        }
			}
			
			//if all checks pass an update is pushed
			return ResponseEntity.status(HttpStatus.OK)
								 .body(repo.save(new Personnel(personnelId,
										 						personnelDTO.getPersonnelName(),
										 						personnelDTO.isCommander(),
										 						personnelDTO.getSquadronId()
										 						)));
			}
		
		
		//Checking if this is coming in through the create route in the controller which auto gives an ID of -1
		else if (personnelId == -1) {
			
			Squadron squad = squadClient.getSquadron(personnelDTO.getSquadronId());
	        
	        // there's probably a less sketchy way to do this... but I need the amount of people in the squad so...
	        ResponseEntity<Iterable<Personnel>> peeps = this.getAllInSquad(squad.getSquadronId());
	        ArrayList<Personnel> people = new ArrayList<Personnel>((Collection<? extends Personnel>) peeps.getBody());
	        
	        //if the squad is already full (or over full) it rejects the request
	        if(squad.getMaxCapacity() <= people.size()) {
	        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	        }
	        
				//if all checks pass a create is pushed
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
	
	
	//deleting on person	
	public ResponseEntity<Void> deleteOne(int personnelId) {
		repo.deleteById(personnelId);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(null);
	}
	
	

}

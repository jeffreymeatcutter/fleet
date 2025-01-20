package com.skillstorm.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.dtos.PersonnelDTO;
import com.skillstorm.models.Personnel;
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
	
	public ResponseEntity<Personnel> updateOrCreate(int personnelId, PersonnelDTO personnelDTO){
		if(repo.existsById(personnelId)){
			return ResponseEntity.status(HttpStatus.OK)
								 .body(repo.save(new Personnel(personnelId,
										 						personnelDTO.getPersonnelName(),
										 						personnelDTO.isCommander(),
										 						personnelDTO.getSquadronId()
										 						)));
		}
			else if (personnelId == -1) {
				return ResponseEntity.status(HttpStatus.CREATED)
						 .body(repo.save(new Personnel(0,
								 						personnelDTO.getPersonnelName(),
								 						personnelDTO.isCommander(),
								 						personnelDTO.getSquadronId()
								 						)));
			}
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

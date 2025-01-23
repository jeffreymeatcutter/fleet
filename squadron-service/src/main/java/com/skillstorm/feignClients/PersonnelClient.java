package com.skillstorm.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.skillstorm.models.Personnel;

@FeignClient(name = "personnel-service")
public interface PersonnelClient {
	@GetMapping("personnel/squadron/{id}")
	public Personnel[] getPersonnel(@PathVariable int id);
	
	@PutMapping("personnel/{id}")
	public Personnel changePersonnel( @PathVariable int id, @RequestBody Personnel personnel);
}

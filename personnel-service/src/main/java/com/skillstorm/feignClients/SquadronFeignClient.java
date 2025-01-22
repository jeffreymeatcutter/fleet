package com.skillstorm.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.skillstorm.models.Squadron;

@FeignClient(name = "squadron-service")
public interface SquadronFeignClient {
	
	@GetMapping("/squadron/{squadId}")
	public Squadron getSquadron(@PathVariable int squadId);

}

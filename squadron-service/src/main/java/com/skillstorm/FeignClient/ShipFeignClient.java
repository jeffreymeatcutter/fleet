package com.skillstorm.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.skillstorm.models.entities.Ship;

@FeignClient(name = "ship-service") 
public interface ShipFeignClient {

    @GetMapping ("/ships/squadron/{id}")
    public Iterable<Ship> getShipsBySquadron(@PathVariable("id") int squadronId);
    
    @PutMapping("ships/{id}")
    public Ship update(@PathVariable int id, @RequestBody Ship ship);
}
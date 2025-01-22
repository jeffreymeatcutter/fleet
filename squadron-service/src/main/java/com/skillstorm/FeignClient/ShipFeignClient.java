package com.skillstorm.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.skillstorm.models.Ship;

@FeignClient(name = "ship-service") 
public interface ShipFeignClient {

    @GetMapping ("/ships/squadron/{squadronId}")
    Iterable<Ship> getShipsBySquadron(@PathVariable("squadronId") int squadronId);
}
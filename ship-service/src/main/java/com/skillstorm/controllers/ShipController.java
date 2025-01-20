package com.skillstorm.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillstorm.dtos.ShipDTO;
import com.skillstorm.models.Ship;
import com.skillstorm.services.ShipService;

import java.util.List;

@RestController
@RequestMapping("/ships")
public class ShipController {

    private final ShipService service;

    public ShipController(ShipService service) {
        this.service = service;
    }

    // Find all 
    @GetMapping
    public ResponseEntity<Iterable<Ship>> findAll() {
        return service.findAll();
    }

    // Find by ID
    @GetMapping("/{shipId}")
    public ResponseEntity<Ship> findById(@PathVariable int shipId) {
        return service.findById(shipId);
    }

    // Create
    @PostMapping
    public ResponseEntity<Ship> create(@RequestBody ShipDTO shipDTO) {
        return service.create(shipDTO);
    }

    // Update
    @PutMapping("/{shipId}")
    public ResponseEntity<Ship> update(@PathVariable int shipId, @RequestBody ShipDTO shipDTO) {
        return service.update(shipId, shipDTO);
    }

    // Delete
    @DeleteMapping("/{shipId}")
    public ResponseEntity<Void> delete(@PathVariable int shipId) {
        return service.delete(shipId);
    }
}


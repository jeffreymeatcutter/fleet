package com.skillstorm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ship")
public class Ship { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "ship_id")
	private int shipId;
	
	@Column(name = "ship_name")
	private String shipName;
	
    @Column(name = "ship_type")
    private String shipType;
    
    @Column(name = "squadron_id")
	private int squadronId;

    public Ship() {
    	super();
    }

	public Ship(int shipId, String shipName, String shipType, int squadronId) { 
		this.shipId = shipId;
		this.shipName = shipName;
		this.shipType = shipType;
		this.squadronId = squadronId;
	}

	public int getShipId() {
		return shipId;
	}

	public void setShipId(int shipId) {
		this.shipId = shipId;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}

	public int getSquadronId() {
		return squadronId;
	}

	public void setSquadronId(int squadronId) {
		this.squadronId = squadronId;
	}

	@Override
	public String toString() {
		return "Ship [shipId=" + shipId + ", shipName=" + shipName + ", shipType=" + shipType + ", squadronId="
				+ squadronId + "]";
	}
    
    
    
    
    
}

package com.skillstorm.models.entities;


public class Ship {

	private int shipId;
	private String shipName;
    private String shipType;
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

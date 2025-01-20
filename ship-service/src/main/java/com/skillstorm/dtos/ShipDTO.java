package com.skillstorm.dtos;
 

public class ShipDTO {

    private String shipName;
    private String shipType;  
    private int squadronId;

    public ShipDTO() {}

    public ShipDTO(String shipName, String shipType, int squadronId) {
        this.shipName = shipName;
        this.shipType = shipType;
        this.squadronId = squadronId;
    }

    // Getters and Setters
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
        return "ShipDTO [shipName=" + shipName + ", shipType=" + shipType + ", squadronId=" + squadronId + "]";
    }
}

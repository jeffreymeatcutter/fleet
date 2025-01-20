package com.skillstorm.dtos;

public class SquadronDTO {
	private String squadronName;
	private int maxCapacity;
	
	public SquadronDTO(String squadronName, int maxCapacity) {
		super();
		this.squadronName = squadronName;
		this.maxCapacity = maxCapacity;
	}

	public String getSquadronName() {
		return squadronName;
	}

	public void setSquadronName(String squadronName) {
		this.squadronName = squadronName;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
		
}

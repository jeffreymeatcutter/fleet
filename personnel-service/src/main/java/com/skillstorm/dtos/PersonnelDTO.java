package com.skillstorm.dtos;

public class PersonnelDTO {
	private String personnelName;
	private boolean isCommander;
	private int squadronId;
	
	public PersonnelDTO(int personnelId, String personnelName, boolean isCommander, int squadronId) {
		super();
		this.personnelName = personnelName;
		this.isCommander = isCommander;
		this.squadronId = squadronId;
	}

	public String getPersonnelName() {
		return personnelName;
	}

	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}

	public boolean isCommander() {
		return isCommander;
	}

	public void setCommander(boolean isCommander) {
		this.isCommander = isCommander;
	}

	public int getSquadronId() {
		return squadronId;
	}

	public void setSquadronId(int squadronId) {
		this.squadronId = squadronId;
	}

	@Override
	public String toString() {
		return "PersonnelDTO [personnelName=" + personnelName + ", isCommander=" + isCommander + ", squadronId="
				+ squadronId + "]";
	}
	
	
	

}

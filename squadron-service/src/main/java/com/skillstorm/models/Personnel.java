package com.skillstorm.models;

public class Personnel {

	private int personnelId;
	private String personnelName;
	private boolean isCommander;
	private int squadronId;

	public Personnel() {
		super();
	}

	public Personnel(int personnelId, String personnelName, boolean isCommander, int squadronId) {
		super();
		this.personnelId = personnelId;
		this.personnelName = personnelName;
		this.isCommander = isCommander;
		this.squadronId = squadronId;
	}

	public int getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(int personnelId) {
		this.personnelId = personnelId;
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
		return "Personnel [personnelId=" + personnelId + ", personnelName=" + personnelName + ", isCommander="
				+ isCommander + ", squadronId=" + squadronId + "]";
	}
	
}

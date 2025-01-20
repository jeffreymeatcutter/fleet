package com.skillstorm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "squadron")
public class Squadron {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "squadron_id")
	private int squadronId;
	
	@Column(name = "squadron_name")
	private String squadronName;
	
	@Column(name = "max_capacity")
	private int maxCapacity;

	public Squadron() {
		super();
	}

	public Squadron(int squadronId, String squadronName, int maxCapacity) {
		super();
		this.squadronId = squadronId;
		this.squadronName = squadronName;
		this.maxCapacity = maxCapacity;
	}

	public int getSquadronId() {
		return squadronId;
	}

	public void setSquadronId(int squadronId) {
		this.squadronId = squadronId;
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

	@Override
	public String toString() {
		return "Squadron [squadronId=" + squadronId + ", squadronName=" + squadronName + ", maxCapacity=" + maxCapacity
				+ "]";
	}
	
		

}

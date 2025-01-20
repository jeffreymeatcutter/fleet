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
@Table(name = "personnel")
public class Personnel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "personnel_id")
	private int personnelId;
	
	@Column(name = "personnel_name")
	private String personnelName;
	
	@Column(name = "isCommander")
	private boolean isCommander;
	
//	@ManyToOne
//	@JoinColumn(name = "squadron_id", referencedColumnName = "squadron_id")
//	@JsonIgnoreProperties({"personnel"})
//	private Squadron squadron;
	
	@Column(name = "squadron_id")
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

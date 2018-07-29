package com.hemalatha.db.application.roster.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Player {

	private String id;
	private String name;
	private String position;
	private double salary;
	private Collection<Team> teams;

	public Player() {
	}

	public Player(String id, String name, String position, double salary) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;

	}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@ManyToMany(mappedBy = "players")
	public Collection<Team> getTeams() {
		return teams;
	}

	public void setTeams(Collection<Team> teams) {
		this.teams = teams;
	}

	public void addTeam(Team team){
		this.getTeams().add(team);
	}

	public void dropTeam(Team team){
		this.getTeams().remove(team);
	}

}

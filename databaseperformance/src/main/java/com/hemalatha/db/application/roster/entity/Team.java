package com.hemalatha.db.application.roster.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Collection;

@Entity
public class Team {

	private String id;
	private String name;
	private String city;
	private Collection<Player> players;
	private League league;

	public Team() {
	}

	public Team(String id, String name, String city) {
		this.id = id;
		this.name = name;
		this.city = city;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@ManyToMany
	@JoinTable(
			name = "TEAM_PLAYER",
			joinColumns = @JoinColumn(name = "TEAM_ID",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "PLAYER_ID",referencedColumnName = "id")
	)
	public Collection<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Collection<Player> players) {
		this.players = players;
	}

	@ManyToOne
	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public void addPlayer(Player player){
		this.getPlayers().add(player);
	}

	public void dropPlayer(Player player){
		this.getPlayers().remove(player);
	}
}

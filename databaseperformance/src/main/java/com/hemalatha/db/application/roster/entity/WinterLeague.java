package com.hemalatha.db.application.roster.entity;

import javax.persistence.Entity;

@Entity
public class WinterLeague extends League{

	public WinterLeague() {
	}

	public WinterLeague(String id,String name,String sport) {
		this.id = id;
		this.name = name;
		if(sport.equalsIgnoreCase("hockey") ||
				sport.equalsIgnoreCase("skiing") ||
				sport.equalsIgnoreCase("snowboarding")){
			this.sport = sport;
		}
	}

}

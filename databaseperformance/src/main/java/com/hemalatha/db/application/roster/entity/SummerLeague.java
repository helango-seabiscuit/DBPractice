package com.hemalatha.db.application.roster.entity;

import javax.persistence.Entity;

@Entity
public class SummerLeague  extends League{

	public SummerLeague() {
	}

	public SummerLeague(String id,String name,String sport) {
		this.id = id;
		this.name = name;
		if(sport.equalsIgnoreCase("swimming") ||
				sport.equalsIgnoreCase("soccer") ||
				sport.equalsIgnoreCase("basketball") ||
				sport.equalsIgnoreCase("baseball") ){
			this.sport = sport;
		}
	}

}

package com.hemalatha.db.application.roster.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RosterMain {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RosterAppln");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	}
}

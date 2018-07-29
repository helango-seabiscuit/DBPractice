package com.hemalatha.db.application.order.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OrderAppln {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OrderAppln");
		EntityManager manager = emf.createEntityManager();
		manager.flush();
	}
}

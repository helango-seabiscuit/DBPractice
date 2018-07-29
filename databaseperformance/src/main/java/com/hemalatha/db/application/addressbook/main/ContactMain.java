package com.hemalatha.db.application.addressbook.main;

import com.hemalatha.db.application.addressbook.entity.Contact;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class ContactMain {

	public static void main(String[] args) {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ContactAppln");
		EntityManager em = managerFactory.createEntityManager();

		em.getTransaction().begin();
		Contact contact = new Contact();
		contact.setBirthday(new Date());
		contact.setHomePhone("890-453-45678");
		em.persist(contact);
		em.getTransaction().commit();
	}
}

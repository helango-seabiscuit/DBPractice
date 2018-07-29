package com.hemalatha.db.performance;

import com.hemalatha.db.performance.model.*;
import com.hemalatha.db.performance.model.hierarchy.joined.ContractEmployee;
import com.hemalatha.db.performance.model.hierarchy.joined.FullTimeEmployee;
import com.hemalatha.db.performance.model.hierarchy.single.Car;
import com.hemalatha.db.performance.model.hierarchy.single.Motorbike;
import com.hemalatha.db.performance.model.hierarchy.single.Vehicle;
import com.hemalatha.db.performance.model.hierarchy.tableperclass.Hershleys;
import com.hemalatha.db.performance.model.hierarchy.tableperclass.Kitkat;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class HibernateBootProvider {

    public static void main(String[] args) {
       // EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPU");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPGPU");
        EntityManager manager = emf.createEntityManager();

//        manager.getTransaction().begin();
//        Post p = manager.createQuery("select p from Post p", Post.class).getResultList().get(0);
//        System.out.println(p);
//        manager.getTransaction().commit();
//        manager.close();
//        emf.close();

   //     testManyToOneRelationship(manager);

     //   testManyToManyRelationshipWithExtraFields(manager);

     //   testEmbeddedId(manager);

     //   testCompositeKey(manager);

        testSingleInheritance(manager);

     //   testJoinedInheritance(manager);

     //   testTablePerClassInheritance(manager);

        emf.close();
    }

    private static void testTablePerClassInheritance(EntityManager manager) {
        manager.getTransaction().begin();
        Kitkat kitkat = new Kitkat();
        kitkat.setName("kitkat");
        kitkat.setType("bar");

        Hershleys hershleys = new Hershleys();
        hershleys.setBrand("hershley");
        hershleys.setName("hershley");

        manager.persist(kitkat);
        manager.persist(hershleys);

        manager.getTransaction().commit();
    }

    private static void testJoinedInheritance(EntityManager manager) {
        manager.getTransaction().begin();
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee();
        fullTimeEmployee.setVacation_hours(5);
        fullTimeEmployee.setName("John");

        ContractEmployee contractEmployee = new ContractEmployee();
        contractEmployee.setHours(45);
        contractEmployee.setName("Mike");

        manager.persist(fullTimeEmployee);
        manager.persist(contractEmployee);

        manager.getTransaction().commit();
    }

    private static void testSingleInheritance(EntityManager manager) {
        manager.getTransaction().begin();
        Car car = new Car();
        car.setDoors(4);

        Motorbike motorbike = new Motorbike();
        motorbike.setSeats(2);

        manager.persist(car);
        manager.persist(motorbike);

        List<Vehicle> vehicles = manager.createQuery("select v from Vehicle v").getResultList();
        for(Vehicle v:vehicles){
            if(v instanceof  Car){
                Car c = (Car) v;
                System.out.println(v.getId() +"instance of car");
                System.out.println(c.getDoors() +  " doors");
            }
            else if(v instanceof  Motorbike){
                Motorbike motorbike1 = (Motorbike) v;
                System.out.println(v.getId() +"instance of motobike");
                System.out.println(motorbike1.getSeats()+ " seats");
            }else{
                System.out.println(v.getId() +" couldnt identify the instance");
            }
        }

        manager.getTransaction().commit();
    }

    private static void testCompositeKey(EntityManager manager) {
        manager.getTransaction().begin();
        Dog dog = new Dog();
        dog.setName("Tommy");
//        DogHouse dogHouse = new DogHouse();
//        dogHouse.setBrand("Pomerian");
//        dogHouse.setDog(dog);

        DogHouseB dogHouse = new DogHouseB();
        dogHouse.setBrand("German Shephard");
        dogHouse.setDog(dog);

        manager.persist(dog);
        manager.persist(dogHouse);

        manager.getTransaction().commit();
    }

    private static void testEmbeddedId(EntityManager manager) {
        manager.getTransaction().begin();
        CowId cowId = new CowId();
        cowId.setBreed("Delhi");
        cowId.setId(1);

        Cow cow = new Cow();
        cow.setCowId(cowId);
        cow.setName("Raasu");

        manager.persist(cow);
        manager.getTransaction().commit();
    }

    private static void testManyToManyRelationshipWithExtraFields(EntityManager manager) {
        manager.getTransaction().begin();
        Cellular cellular = new Cellular();
        cellular.setNumber(2345);

        Person p = new Person();
        p.setName("Hema");
        p.setCellular(cellular);
        Set<String> emails = new HashSet();
        emails.add("helan@gmail.com");
        emails.add("a@b.com");
        p.setEmails(emails);
        List<CarBrands> carbrands = Arrays.asList(CarBrands.FORD, CarBrands.SUZUKI);
        p.setBrands(carbrands);

        Dog dog = new Dog();
        dog.setName("Jackie");

        PersonDog personDog = new PersonDog();
        personDog.setDog(dog);
        personDog.setPerson(p);
        personDog.setAdoptionDate(new Date());

        p.getDogs().add(personDog);
        dog.getPersons().add(personDog);

        manager.persist(cellular);
        manager.persist(p);
        manager.persist(dog);
        manager.persist(personDog);
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        Person p1 = manager.find(Person.class, p.getId());
        System.out.println(p1.getDogs().size());

        PersonDogId pdId = new PersonDogId();
        pdId.setDog(dog.getId());
        pdId.setPerson(p.getId());


        PersonDog p2 = manager.find(PersonDog.class, pdId);
        System.out.println(p2.getDog().getId());
        System.out.println(p2.getPerson().getId());
        manager.getTransaction().commit();

        manager.close();
    }

    private static void testManyToOneRelationship(EntityManager em){
        em.getTransaction().begin();
        Book book = new Book();
        book.setTitle("Effective Java");

        Review review = new Review();
        review.setComment("Awesome");
        em.persist(review);

        book.addReviews(review);

        em.persist(book);


        em.getTransaction().commit();
        em.close();
    }
}

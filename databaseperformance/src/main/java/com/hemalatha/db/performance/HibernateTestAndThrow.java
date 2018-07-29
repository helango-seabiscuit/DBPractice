package com.hemalatha.db.performance;


import com.hemalatha.db.performance.model.collectionmapping.Department;
import com.hemalatha.db.performance.model.collectionmapping.Employee;
import com.hemalatha.db.performance.model.practicethrow.Student;
import com.hemalatha.db.performance.model.practicethrow.throwsoon.Location;
import com.hemalatha.db.performance.model.practicethrow.throwsoon.PersonDetails;


import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PessimisticLockScope;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class HibernateTestAndThrow {

	public static void main(String[] args) {
		Properties properties = new Properties();
		//properties.put("javax.persistence.sharedCache.mode","ENABLE_SELECTIVE");
		properties.put("javax.persistence.sharedCache.mode","ALL");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PGCollection",
				properties);
		EntityManager manager = emf.createEntityManager();

		//CriteriaBuilderUtil.m(manager);

//        manager.createQuery("select  c.instructor.name from Student s, IN(s.courses) c where s.id = ?1")
//				.setParameter(1,1)
//				.getResultList();

//		manager.getTransaction().begin();
//		Student student = new Student();
//		student.setGrade("A");
//		StudentInfo info = new StudentInfo();
//		student.setInfo(info);
//		info.setStudent(student);
//    //    manager.persist(info);
//		manager.persist(student);
//		System.out.println(student);
//		manager.getTransaction().commit();
//		manager.detach(student);
//		manager.clear();
//		manager.getTransaction().begin();
//
//       //  Student s = manager.find(Student.class,0,LockModeType.OPTIMISTIC);
//		manager.merge(student);
//		Student t = new Student();
//		manager.merge(t);
//		System.out.println(manager.contains(student));
//		criteriaQueryHaving(manager);
//		//criteriaquery2(manager);
//		manager.getTransaction().commit();
//
//
//		List res = manager.createNativeQuery("select grade from student s where s.grade=?1")
//				.setParameter(1,'A').getResultList();

		//validateBean();
		manager.getTransaction().begin();
		PersonDetails personDetails = new PersonDetails();
		personDetails.setId(1);
		Location location = new Location("chennai","tnagar");
		Location location2 = new Location("coimbatore","saibaba");
		Location location3 = new Location("madurai","tvkoil");
		List<Location> locationList = new ArrayList<>();
		locationList.add(location);
		locationList.add(location2);
		locationList.add(location3);
		personDetails.setLocations(locationList);
		manager.persist(personDetails);
		manager.getTransaction().commit();
		manager.getTransaction().begin();

		manager.getTransaction().commit();
		manager.clear();

		manager.getTransaction().begin();
		PersonDetails d = manager.find(PersonDetails.class,personDetails.getId());
		d.getLocations().add(new Location("trichy","periyarnagar"));
		manager.getTransaction().commit();
		manager.close();
		emf.close();

	}

	private static void validateBean(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

	}


	private static void criteriaQueryHaving(EntityManager manager) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createTupleQuery();
		Root<Student> studentRoot = cq.from(Student.class);
		cq.orderBy(cb.asc(studentRoot.get("grade")),cb.asc(studentRoot.get("name")));
		cq.groupBy(studentRoot.get("grade"));
		cq.having(cb.in(studentRoot.get("grade")).value("A").value("B").value("C"));
		cq.select(cb.tuple(studentRoot.get("grade"),cb.count(studentRoot)));
		//cq.multiselect(cb.tuple(studentRoot.get("grade"),cb.count(studentRoot)));
		manager.createQuery(cq).getResultList();
	}

	private static void criteriaquery2(EntityManager manager){
		int maxAge =5;
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Department> cq = builder.createQuery(Department.class);
		ParameterExpression<Integer> limitParam = builder.parameter(Integer.class,"ageLimit");
		Root<Employee> employeeRoot = cq.from(Employee.class);
		cq.select(employeeRoot.get("department")).distinct(true);
		cq.where(builder.ge(employeeRoot.get("age"),limitParam));
		TypedQuery<Department> typedQuery = manager.createQuery(cq);
		typedQuery.setParameter("ageLimit",maxAge);

	}

	private static void criteriaquery3(EntityManager manager){
		/**
		 *
		 CriteriaBuilder builder = manager.getCriteriaBuilder();
		 CriteriaQuery<Lecturer> cq = builder.createQuery(Lecturer.class);
		 Root<Lecturer> lecturer = cq.from(Lecturer.class);
		 cq.select(lecturer).distinct(true);
		 Join<Lecturer,Student> student = lecturer.join("student");
		 Subquery<Double> subquery = cq.subquery(Double.class);
		 Root<Student> studentSub = subquery.from(Student.class);
		 subquery.select(criteriaBuilder.max(studentSub.get("mark")));
		 cq.where(builder.equal(student.get("mark"),subquery));
		 TypedQuery<Department> typedQuery = manager.createQuery(cq);
		 List<Lecturer> result = typedQuery.getResultList();
		 }
		 */


	}
}

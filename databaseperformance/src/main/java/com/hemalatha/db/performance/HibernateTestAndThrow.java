package com.hemalatha.db.performance;


import com.hemalatha.db.performance.model.collectionmapping.Department;
import com.hemalatha.db.performance.model.collectionmapping.Employee;
import com.hemalatha.db.performance.model.practicethrow.Student;
import com.hemalatha.db.performance.model.practicethrow.throwsoon.Book;
import com.hemalatha.db.performance.model.practicethrow.throwsoon.Invoice;
import com.hemalatha.db.performance.model.practicethrow.throwsoon.Line;
import com.hemalatha.db.performance.model.practicethrow.throwsoon.Publisher;
import com.hemalatha.db.performance.model.practicethrow.throwsoon.Tax;


import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.PersistenceUtil;
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
import java.time.LocalDate;
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
		Invoice invoice = new Invoice("An invoice for John Smith");
		manager.persist(invoice);

		manager.persist(new Line("1 pen - 5€", invoice));
		manager.persist(new Tax("21%", invoice));

		manager.getTransaction().commit();
		manager.clear();
		Invoice res = manager.find(Invoice.class,1);
		System.out.println(emf.getPersistenceUnitUtil().isLoaded(res,"lines"));
		System.out.println(emf.getPersistenceUnitUtil().isLoaded(res,"taxes"));

		//manager.clear();
	//	Invoice res1 = manager.find(Invoice.class,1);
		manager.refresh(res);
		System.out.println(emf.getPersistenceUnitUtil().isLoaded(res,"lines"));
		System.out.println(emf.getPersistenceUnitUtil().isLoaded(res,"taxes"));
		//manager.close();

		manager.getTransaction().begin();
		Publisher p = new Publisher();
		p.setCode("pub_04");
		manager.persist(p);
		Book book = new Book();
		book.setPublishingDate(LocalDate.now());
		book.setTitle("Java Performance");
		book.setPublisher(p);
		manager.persist(book);
		manager.getTransaction().commit();
		Book b = manager.find(Book.class,book.getId());
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

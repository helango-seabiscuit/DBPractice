package com.hemalatha.db.performance;

import com.hemalatha.db.performance.dto.EmpDto;
import com.hemalatha.db.performance.model.*;
import com.hemalatha.db.performance.model.collectionmapping.Department;
import com.hemalatha.db.performance.model.collectionmapping.Employee;
import com.hemalatha.db.performance.model.collectionmapping.EmployeeInfo;
import com.hemalatha.db.performance.model.collectionmapping.PrintJob;
import com.hemalatha.db.performance.model.collectionmapping.PrintQueue;
import com.hemalatha.db.performance.model.collectionmapping.SecurityInfo;
import com.hemalatha.db.performance.service.EmployeeService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

//psql -d test -h localhost -p 5432
public class HibernateBootPostgresProvider {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPGPU");
        EntityManager manager = emf.createEntityManager();
        EntityManager manager2 = emf.createEntityManager();

        Map map = emf.getProperties();


       // testAttributeConverter(manager);
       //  testCacheUsage(emf,manager);
       //  testElementCollection(emf,manager);
       //testOrderByColumn(manager);
       // testOnetoOneSameId(manager);
       // testOptimisticLocking(manager,manager2);
       // testSimulEntityOp(manager,manager2);
        //testSimulEntityOp(manager,manager2);
        //testQuery(manager);
        testNamedQuery(manager);
        //testQueryByDifferentManager(manager,manager2);
       // testQueryUsingDto(manager);
        //testBulkUpdateQuery(manager,manager2);
        //testQueryMapCollection(manager);
        emf.close();
    }

    public static void testQueryByDifferentManager(EntityManager manager1,EntityManager manager2){
        createEmpDep(manager1);
        manager1.getTransaction().begin();
        TypedQuery query = manager1.createNamedQuery("findEmployeeByDepartment",Employee.class);
        manager1.close();
        manager1.getTransaction().begin();
        List<Employee> employees = query.setParameter("depName","Computer").getResultList();
        manager1.close();
        System.out.println(employees.size());
    }

    public static void testQueryMapCollection(EntityManager manager){
        manager.getTransaction().begin();
        Employee employee = new Employee();
        employee.setFullName("Test 05");
        List<String> names = Arrays.asList("tester","qa","qe");
        employee.setNickNames(new HashSet(names));
        Map<Employee.PhoneType,String> phoneNums = new HashMap<>();
        phoneNums.put(Employee.PhoneType.Mobile,"23456677");
        phoneNums.put(Employee.PhoneType.Home,"02452423");
        phoneNums.put(Employee.PhoneType.Work,"77779905600");
        employee.setPhoneNumbers(phoneNums);
       // manager.persist(phoneNums);
        manager.persist(employee);

        List e = manager.createQuery("select e.fullName, KEY(p),value(p) from Employee e JOIN e.phoneNumbers p").getResultList();
        System.out.println(e.size());

    }

    public static void testNamedQuery(EntityManager manager){
        createEmpDep(manager);
        List<Employee> employees = manager.createNamedQuery("findEmployeeByDepartment",Employee.class)
                .setHint("javax.persistence.query.timeout", 5000)
                .setParameter("depName","Computer")
                .getResultList();
        System.out.println(employees.size());
    }

    private static void testBulkUpdateQuery(EntityManager manager,EntityManager manager2){
        createEmpDep(manager);
        manager.getTransaction().begin();
        Employee employee = manager
                .createQuery("select e from Employee e where e.fullName='Test 04'",Employee.class)
                .getSingleResult();
        employee.setFullName("Test ups");

        Department department = new Department();
        department.setName("Mechanics");
        manager.persist(department);
        Employee employee1 = new Employee();
        employee1.setFullName("Test A");
        employee1.setDepartment(department);
        manager.persist(department);
        manager.persist(employee1);


        manager.createQuery("delete from Employee e where e.id=:id").setParameter("id",employee1.getId()).executeUpdate();
        manager.createQuery("Update Employee set fullName=:fullName where id= :id")
                .setParameter("fullName","Test 10")
                .setParameter("id",employee.getId())
                .executeUpdate();
        manager.flush();
        manager.getTransaction().commit();
        manager.getTransaction().begin();
        List<Employee> employeeList = manager
                .createQuery("select e from Employee e",Employee.class)
                .getResultList();

        manager.getTransaction().commit();
        manager.close();
    }

    private static void printResult(Object r){
    	if(r == null){
			System.out.println("NULL");
		}
		else  if( r instanceof Object[]){
    		Object[] row = (Object[]) r;
			System.out.println("[");
    		for (int i=0;i<row.length;i++){
				printResult(row[i]);
			}
			System.out.println("]");
		}else if (r instanceof Long ||
				r instanceof  Double ||
				r instanceof String ){
			System.out.print(r.getClass().getName()+":"+r);
		}else{
			System.out.print(ReflectionToStringBuilder.toString(r, ToStringStyle.SHORT_PREFIX_STYLE));
		}
	}

    private static void testQueryUsingDto(EntityManager manager){
        createEmpDep(manager);
        List<EmpDto> employees = manager
                .createQuery("select new com.hemalatha.db.performance.dto.EmpDto(e.fullName) from Employee e join e.department d where d.name='Computer'",
                        EmpDto.class)
                .getResultList();

        List employeeObjects = manager
                .createQuery("select e from Employee e join e.department d where d.name='Computer'")
                .getResultList();
        for(Object o:employeeObjects) {
            printResult(o);
        }


        System.out.println(Arrays.deepToString(employees.toArray()));

    }

    private static void testSimulEntityOp(EntityManager manager, EntityManager manager2){
        createEmpDep(manager);
        EmployeeService service = new EmployeeService(manager,manager2);
        long total = service.generateDepSalaryReport(Arrays.asList(1));
        System.out.println(total);
    }

    private static void createEmpDep(EntityManager manager) {
        manager.getTransaction().begin();
        Department department = new Department();
        department.setName("Computer");
        manager.persist(department);
        Employee employee1 = new Employee();
        employee1.setFullName("Test 04");
        List<String> roles = Arrays.asList("r1","r2","r3","r4");
        SecurityInfo securityInfo = new SecurityInfo();
        securityInfo.setSecurityCleared(Boolean.FALSE);
        securityInfo.setRoles(roles);
        employee1.setSecurityInfo(securityInfo);
        employee1.setDepartment(department);
        Map<Employee.PhoneType,String> phoneNums = new HashMap<>();
        phoneNums.put(Employee.PhoneType.Mobile,"234rtwrtr34");
        phoneNums.put(Employee.PhoneType.Home,"02452423");
        phoneNums.put(Employee.PhoneType.Work,"77779905600");
        employee1.setPhoneNumbers(phoneNums);

        Employee employee2 = new Employee();
        employee2.setFullName("Test 05");
        employee2.setDepartment(department);
        SecurityInfo securityInfo2 = new SecurityInfo();
        securityInfo2.setSecurityCleared(Boolean.TRUE);
        employee2.setSecurityInfo(securityInfo2);
        manager.persist(employee1);
        manager.persist(employee2);
        HashMap<Integer,Employee> d = new HashMap<>();
        d.put(employee1.getId(),employee1);
        d.put(employee2.getId(),employee2);
       // department.setEmployees(d);


        manager.getTransaction().commit();
    }

    private static  void testElementCollection(EntityManagerFactory factory, EntityManager manager){
        manager.getTransaction().begin();
        Employee employee = new Employee();
        employee.setFullName("Test 05");
        List<String> names = Arrays.asList("tester","qa","qe");
        employee.setNickNames(new HashSet(names));
        Map<Employee.PhoneType,String> phoneNums = new HashMap<>();
        phoneNums.put(Employee.PhoneType.Mobile,"23456677");
        phoneNums.put(Employee.PhoneType.Home,"02452423");
        phoneNums.put(Employee.PhoneType.Work,"77779905600");
        employee.setPhoneNumbers(phoneNums);
        manager.persist(employee);

        Employee e = manager.find(Employee.class, employee.getId());
        System.out.println(e.getNickNames());
        manager.getTransaction().commit();

    }

    private static void testCacheUsage(EntityManagerFactory factory, EntityManager manager){
        manager.getTransaction().begin();

        OnlineCourse course = new OnlineCourse();
        course.setTitle("Kafka Internals II");
        course.setVideoduration(Duration.ofHours(3));

        manager.persist(course);
        manager.getTransaction().commit();
        manager.close();
        factory.getCache().evictAll();

        EntityManager manager1 = factory.createEntityManager();

        OnlineCourse c1 = manager1.find(OnlineCourse.class, course.getId());
        System.out.println("Calling second time");

        EntityManager manager2 = factory.createEntityManager();
        OnlineCourse c2 = manager2.find(OnlineCourse.class, course.getId());

        //query non available entity
        OnlineCourse c3 = manager1.find(OnlineCourse.class, course.getId()+1);
        OnlineCourse c4 = manager2.find(OnlineCourse.class, course.getId()+2);

    }

    private static void testOnetoOneSameId(EntityManager manager){
        manager.getTransaction().begin();
        Employee employee = new Employee();
        employee.setFullName("Test EmpInfo");
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setEmployee(employee);
        employeeInfo.setDescription("test desc");
        manager.persist(employee);
        manager.persist(employeeInfo);
        manager.getTransaction().commit();

    }

    private static void testOptimisticLocking(EntityManager manager, EntityManager manager2){
        manager.getTransaction().begin();
        Employee employee = new Employee();
        employee.setFullName("Test EmpInfo");
        manager.persist(employee);
        manager.getTransaction().commit();

        Employee e1 = manager.find(Employee.class,employee.getId());
        Employee e2 = manager2.find(Employee.class,employee.getId());
        manager.getTransaction().begin();
        e1.setFullName("Test thread1");
        manager.persist(e1);
        manager.getTransaction().commit();
        e1 = manager.find(Employee.class,employee.getId());

        manager2.getTransaction().begin();
        e2.setFullName("Test thread2");
        manager2.persist(e2);
        manager2.getTransaction().commit();


    }

    private static void testOrderByColumn(EntityManager manager){
        manager.getTransaction().begin();
        PrintQueue queue = new PrintQueue();
        queue.setName("print#1");
        PrintJob job = new PrintJob();
        job.setQueue(queue);
        PrintJob job2 = new PrintJob();
        job2.setQueue(queue);
        PrintJob job3 = new PrintJob();
        job3.setQueue(queue);
        queue.setJobs(Arrays.asList(job,job2,job3));
        manager.persist(queue);
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        PrintQueue queue1 = manager.find(PrintQueue.class,queue.getName());
        List<PrintJob> jobs = queue1.getJobs();
        ArrayList a = new ArrayList(jobs);
        a.remove(0);
        queue1.setJobs(a);
        manager.persist(queue1);
        manager.getTransaction().commit();


    }




    private static void testAttributeConverter(EntityManager manager) {
        manager.getTransaction().begin();

       OnlineCourse course = new OnlineCourse();
       course.setTitle("Kafka Internals II");
       course.setVideoduration(Duration.ofHours(3));

        manager.persist(course);
        manager.getTransaction().commit();

//        manager.getTransaction().begin();
        OnlineCourse p1 = manager.find(OnlineCourse.class, course.getId());
        System.out.println(p1.getTitle());
        System.out.println(p1.getVideoduration());


        manager.close();
    }



}

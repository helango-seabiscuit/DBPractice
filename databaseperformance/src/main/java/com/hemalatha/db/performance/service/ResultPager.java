package com.hemalatha.db.performance.service;

import com.hemalatha.db.performance.model.collectionmapping.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class ResultPager {

	private EntityManager manager;

	private String queryName;
	private int currentPage;
	private int maxResults;
	private int pageSize;

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getMaxPages(){
		return maxResults / pageSize;
	}

	public ResultPager(EntityManager manager, String queryName,  int pageSize) {
		this.manager = manager;
		this.queryName = queryName;
		this.pageSize = pageSize;
	}

	public List<Employee> getCurrentResults(){
		List<Employee> employees =  manager.createQuery("select e from Employee e join e.department d where d.name=:depName",Employee.class)
				.setFirstResult((int)(currentPage * pageSize))
				.setMaxResults(pageSize)
				.getResultList();
		currentPage++;
		return employees;
	}
}

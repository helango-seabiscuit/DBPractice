package com.hemalatha.db.performance.model.practicethrow;

import com.hemalatha.db.performance.model.collectionmapping.Department;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class CriteriaBuilderUtil {

	public static void m(EntityManager manager){
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		 CriteriaQuery<DepartmentModel> cq = cb.createQuery(DepartmentModel.class);
		Root<DepartmentModel> root = cq.from(DepartmentModel.class);
//		ParameterExpression<String> p = cb.parameter(String.class);
//		cq.select(root).where(cb.equal(root.get("name"),p));
		root.fetch("address", JoinType.LEFT);
		cq.select(root).distinct(true);

		TypedQuery<DepartmentModel> query = manager.createQuery(cq);
		List<DepartmentModel> result = query.getResultList();
		System.out.println(result.size());
	}
}

package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.Category;

@Repository
public class CategoryRepository extends GenericRepository{

	public boolean isCategoryPresent(String name) {
		return (Long)
				entityManager
				.createQuery("select count(c.id) from Category c where c.name = :na")
				.setParameter("na", name)
				.getSingleResult() == 1 ? true : false;
	}
	
	
	
	public List<Category> fetch() {
		List<Category> list = entityManager.createQuery("select c from Category c").getResultList();
		return list;
	}
	
}
	

package com.lti.repository;

import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository extends GenericRepository{

	public boolean isCategoryPresent(String name) {
		return (Long)
				entityManager
				.createQuery("select count(c.id) from Category c where c.name = :na")
				.setParameter("na", name)
				.getSingleResult() == 1 ? true : false;
	}
}

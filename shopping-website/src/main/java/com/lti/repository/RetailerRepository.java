package com.lti.repository;

import org.springframework.stereotype.Repository;

@Repository
public class RetailerRepository extends GenericRepository{
	

	public boolean isRetailerPresent(String email) {
		return (Long)
				entityManager
				.createQuery("select count(r.id) from Retailer r where r.email = :em")
				.setParameter("em", email)
				.getSingleResult() == 1 ? true : false;
	}
	
	public int fetchIdByEmailAndPassword(String email, String password) {
		return (Integer)
				entityManager
				.createQuery("select r.id from Retailer r where r.email = :em and r.password = :pw")
				.setParameter("em", email)
				.setParameter("pw", password)
				.getSingleResult();
	}
}

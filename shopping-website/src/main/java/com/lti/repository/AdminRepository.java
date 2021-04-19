package com.lti.repository;

import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository extends GenericRepository{
	
	public boolean isAdminPresent(String email) {
		return(Long)
				entityManager
				.createQuery("select count(a.id) from Admin a where a.email = :email")
				.setParameter("email", email)
				.getSingleResult() == 1 ? true : false; 
	}
	
	
	
	public int fetchIdByEmailAndPassword(String email, String password) {
		return (Integer)
				entityManager
				.createQuery("select a.id from Admin a where a.email = :em and a.password = :pw")
				.setParameter("em", email)
				.setParameter("pw", password)
				.getSingleResult();
	}

}

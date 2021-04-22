package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.Customer;
import com.lti.entity.Retailer;

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
	
	
	public List<Customer> fetchTotalCustomer() {
	   return 
			   entityManager
			   .createQuery("select c from Customer c")
			   .getResultList();
	}
	
	
	public List<Retailer> fetchTotalRetailer() {
		return 
				entityManager
				.createQuery("select r from Retailer r")
				.getResultList();
	}
	

}

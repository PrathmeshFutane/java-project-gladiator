package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.Quantity;

@Repository
public class QuantityRepository extends GenericRepository{

	public List<Quantity> fetch() {
		List<Quantity> list = entityManager.createQuery("select q from Quantity q").getResultList();
		return list;	
	}
}

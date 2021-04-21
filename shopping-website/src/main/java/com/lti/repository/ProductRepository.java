package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.Category;
import com.lti.entity.Product;

@Repository
public class ProductRepository extends GenericRepository{

	public List<Product> fetch() {
		List<Product> list = entityManager.createQuery("select p from Product p").getResultList();
		return list;	
	}
	
	
	public List<Product> fetchByCategory(int categoryId){
		List<Product> list = entityManager
								.createQuery("select p from Product p where p.category.categoryId= :categoryId")
								.setParameter("categoryId", categoryId)
								.getResultList();
		return list;
	}
}

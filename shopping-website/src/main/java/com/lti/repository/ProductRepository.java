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
	
	
	public List<Product> fetchByKeyword(String keyword){
		return entityManager
				.createQuery("select p from Product p where p.productDescription like :keyword")
				.setParameter("keyword", "%"+keyword+"%")
				.getResultList();
	}
	
	public List<Product> fetchByRange(int price1, int price2) {
		return entityManager
				.createQuery("select p from Product p where p.unitPrice >= :price1 and p.unitPrice <= :price2")
				.setParameter("price1",price1)
				.setParameter("price2",price2)
				.getResultList();
	}
	
	public List<Product> fetchByAscending(){
		return entityManager
				.createQuery("select p from Product p order by p.unitPrice asc")
				.getResultList();
	}	
	
	public List<Product> fetchByDescending(){
		return entityManager
				.createQuery("select p from Product p order by p.unitPrice desc")
				.getResultList();
	}	
	
	
}

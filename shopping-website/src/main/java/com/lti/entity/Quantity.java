package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_quantity")
public class Quantity {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int quantityId;
	
	@Column(name = "total_count")
	private int totalCount;

	public int getQuantityId() {
		return quantityId;
	}

	public void setQuantityId(int quantityId) {
		this.quantityId = quantityId;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
	
}

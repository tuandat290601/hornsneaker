package vn.edu.hcmus.hornsneaker.dao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetailsEntity {
	@Column(name = "ORDER_ID", nullable = false)
	private Long id;

	@Column(name = "PRODUCT", nullable = false)
	private String product;

	@Column(name="AMOUNT")
	private int amount;

	//#region getter and setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}		
	//#endregion
		
}

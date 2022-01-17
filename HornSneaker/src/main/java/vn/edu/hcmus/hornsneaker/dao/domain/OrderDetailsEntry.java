package vn.edu.hcmus.hornsneaker.dao.domain;

import javax.persistence.Column;

public class OrderDetailsEntry {
	private String productName;

	private int size;

	private int amount;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	// #endregion
}

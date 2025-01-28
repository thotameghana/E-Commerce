package com.ecommerce.model;

public class Orders {
	private int orderId;
	private int userId;
	private int productId;
	private int totalAmount;
	private String status;
	private String paymentMode;
	
	public Orders() {
	}
	public Orders(int orderId, int userId, int productId, int totalAmount, String status, String paymentMode) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMode = paymentMode;
	}
	public Orders(int userId, int productId, int totalAmount, String status, String paymentMode) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMode = paymentMode;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	@Override
	public String toString() {
		return orderId+"  "+userId+"  "+productId+"  "+totalAmount+"  "+status+" "+paymentMode;
	}
}

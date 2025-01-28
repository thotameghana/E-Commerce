package com.ecommerce.model;

public class OrderItem {
	private int orderItemId;
	private int orderId; 
	private int productItemId;
	private int quantity;
	private int itemTotal;
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}
	public OrderItem(int orderItemId, int orderId, int productItemId, int quantity, int itemTotal) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.productItemId = productItemId;
		this.quantity = quantity;
		this.itemTotal = itemTotal;
	}
	public OrderItem( int orderId, int productItemId, int quantity, int itemTotal) {
		super();
		this.orderId = orderId;
		this.productItemId = productItemId;
		this.quantity = quantity;
		this.itemTotal = itemTotal;
	}
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductItemId() {
		return productItemId;
	}
	public void setProductItemId(int productItemId) {
		this.productItemId = productItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getItemTotal() {
		return itemTotal;
	}
	public void setItemTotal(int itemTotal) {
		this.itemTotal = itemTotal;
	}
	@Override
	public String toString() {
		return orderItemId + " " + orderId + " " + productItemId+ " " + quantity + " " + itemTotal;
	}
	
	
}	

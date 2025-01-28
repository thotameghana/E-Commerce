package com.ecommerce.dao;

import java.util.ArrayList;

import com.ecommerce.model.OrderItem;

public interface OrderItemDao {
	 int insert(OrderItem orderItem);
	 ArrayList<OrderItem> fetchAll();
	 OrderItem fetchSpecific(int orderItemId);
     int update(int orderItemId, int quantity, double price);
     int delete(int orderItemId);
     ArrayList<OrderItem> fetchByOrderId(int orderId);
}

package com.ecommerce.dao;

import java.util.ArrayList;

import com.ecommerce.model.Orders;

public interface OrderDao {
	int insert(Orders order);
	ArrayList<Orders> fetchAll();
	Orders fetchSpecific(int orderId);
	int update(int orderId,String status);
	int delete(int orderId);
	Orders fetchByProductId(int productId);
}

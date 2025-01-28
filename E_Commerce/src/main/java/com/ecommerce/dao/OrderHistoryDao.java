package com.ecommerce.dao;

import java.util.ArrayList;

import com.ecommerce.model.OrderHistory;

public interface OrderHistoryDao {
	 int insert(OrderHistory orderHistory);
	 ArrayList<OrderHistory> fetchAll();
     OrderHistory fetchSpecific(int orderHistoryId);
     int updateStatus(int orderHistoryId, String status);
     int delete(int orderHistoryId);
}

package com.ecommerce.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.ecommerce.dao.OrderDao;
import com.ecommerce.model.Orders;
import com.ecommerce.resources.MyConnection;

public class OrderDaoImpl implements OrderDao {
	
	static Connection con;
	static ArrayList<Orders> orderList=new ArrayList<>();
	static {
		con=MyConnection.connect();
	}
	private static final String INSERT="insert into orders(userId,productId,totalAmount,status,paymentMode) values(?,?,?,?,?)";
	private static final String FETCH_ALL="select * from orders";
	private static final String FETCHONE="select * from orders where orderId=?";
	private static final String UPDATE="update orders set status=? where orderId=?";
	private static final String DELETE="delete from orders where orderId=?";
	private static final String FETCHBYPID="select * from orders where productId=?";
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	@Override
	public int insert(Orders order) {
		try {
			pstmt=con.prepareStatement(INSERT);
			pstmt.setInt(1,order.getUserId());
			pstmt.setInt(2,order.getProductId());
			pstmt.setInt(3,order.getTotalAmount());
			pstmt.setString(4, order.getStatus());
			pstmt.setString(5, order.getPaymentMode());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ArrayList<Orders> fetchAll() {
		try {
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(FETCH_ALL);
			
			return fetchFromTable(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Orders fetchSpecific(int orderId) {
		try {
			pstmt=con.prepareStatement(FETCHONE);
			pstmt.setInt(1, orderId);
			resultSet=pstmt.executeQuery();
			return fetchFromTable(resultSet).get(0);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int update(int orderId, String status) {
		try {
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, status);
			pstmt.setInt(2, orderId);
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(int orderId) {
		try {
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, orderId);
			return pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Orders fetchByProductId(int productId) {
		try {
			pstmt=con.prepareStatement(FETCHBYPID);
			pstmt.setInt(1, productId);
			resultSet=pstmt.executeQuery();
			return orderList.isEmpty() ? null : orderList.get(0);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private ArrayList<Orders> fetchFromTable(ResultSet resultSet) {
		orderList.clear();
		try {
			while(resultSet.next()) {
				orderList.add(new Orders(
						resultSet.getInt("orderId"),
						resultSet.getInt("userId"),
						resultSet.getInt("productId"),
						resultSet.getInt("totalAmount"),
						resultSet.getString("status"),
						resultSet.getString("paymentMode")
						));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}

}

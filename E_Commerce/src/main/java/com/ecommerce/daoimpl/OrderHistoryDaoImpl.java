package com.ecommerce.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.ecommerce.dao.OrderHistoryDao;
import com.ecommerce.model.OrderHistory;
import com.ecommerce.resources.MyConnection;

public class OrderHistoryDaoImpl implements OrderHistoryDao {

    static Connection con;
    static {
        con = MyConnection.connect();
    }

    private static final String INSERT = "INSERT INTO orderhistory(orderId, userId, totalAmount, status) VALUES(?, ?, ?, ?)";
    private static final String FETCH_ALL = "SELECT * FROM orderhistory";
    private static final String FETCH_ONE = "SELECT * FROM orderhistory WHERE orderHistoryId = ?";
    private static final String UPDATE_STATUS = "UPDATE orderhistory SET status = ? WHERE orderHistoryId = ?";
    private static final String DELETE = "DELETE FROM orderhistory WHERE orderHistoryId = ?";

    @Override
    public int insert(OrderHistory orderHistory) {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT)) {
            pstmt.setInt(1, orderHistory.getOrderId());
            pstmt.setInt(2, orderHistory.getUserId());
            pstmt.setInt(3, orderHistory.getTotalAmount());
            pstmt.setString(4, orderHistory.getStatus());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ArrayList<OrderHistory> fetchAll() {
        try (Statement stmt = con.createStatement(); ResultSet resultSet = stmt.executeQuery(FETCH_ALL)) {
            return fetchFromTable(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OrderHistory fetchSpecific(int orderHistoryId) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_ONE)) {
            pstmt.setInt(1, orderHistoryId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                ArrayList<OrderHistory> histories = fetchFromTable(resultSet);
                return histories.isEmpty() ? null : histories.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateStatus(int orderHistoryId, String status) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_STATUS)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, orderHistoryId);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int orderHistoryId) {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE)) {
            pstmt.setInt(1, orderHistoryId);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private ArrayList<OrderHistory> fetchFromTable(ResultSet resultSet) {
        ArrayList<OrderHistory> orderHistories = new ArrayList<>();
        try {
            while (resultSet.next()) {
                orderHistories.add(new OrderHistory(
                    resultSet.getInt("orderHistoryId"),
                    resultSet.getInt("orderId"),
                    resultSet.getInt("userId"),
                    resultSet.getInt("totalAmount"),
                    resultSet.getString("status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderHistories;
    }
}

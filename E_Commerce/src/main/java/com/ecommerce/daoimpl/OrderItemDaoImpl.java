package com.ecommerce.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.ecommerce.dao.OrderItemDao;
import com.ecommerce.model.OrderItem;
import com.ecommerce.resources.MyConnection;

public class OrderItemDaoImpl implements OrderItemDao {
    static Connection con;
    static {
        con = MyConnection.connect();
    }

    private static final String INSERT = "INSERT INTO orderitems(orderId, productId, quantity, price) VALUES(?, ?, ?, ?)";
    private static final String FETCH_ALL = "SELECT * FROM orderitems";
    private static final String FETCH_ONE = "SELECT * FROM orderitems WHERE orderItemId = ?";
    private static final String UPDATE = "UPDATE orderitems SET quantity = ?, price = ? WHERE orderItemId = ?";
    private static final String DELETE = "DELETE FROM orderitems WHERE orderItemId = ?";
    private static final String FETCH_BY_ORDERID = "SELECT * FROM orderitems WHERE orderId = ?";

    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;

    @Override
    public int insert(OrderItem orderItem) {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT)) {
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getProductItemId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setDouble(4, orderItem.getItemTotal());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ArrayList<OrderItem> fetchAll() {
        try (Statement stmt = con.createStatement(); ResultSet resultSet = stmt.executeQuery(FETCH_ALL)) {
            return fetchFromTable(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OrderItem fetchSpecific(int orderItemId) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_ONE)) {
            pstmt.setInt(1, orderItemId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                ArrayList<OrderItem> items = fetchFromTable(resultSet);
                return items.isEmpty() ? null : items.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int update(int orderItemId, int quantity, double price) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE)) {
            pstmt.setInt(1, quantity);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, orderItemId);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int orderItemId) {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE)) {
            pstmt.setInt(1, orderItemId);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ArrayList<OrderItem> fetchByOrderId(int orderId) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_ORDERID)) {
            pstmt.setInt(1, orderId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                return fetchFromTable(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<OrderItem> fetchFromTable(ResultSet resultSet) {
        ArrayList<OrderItem> items = new ArrayList<>();
        try {
            while (resultSet.next()) {
                items.add(new OrderItem(
                    resultSet.getInt("orderItemId"),
                    resultSet.getInt("orderId"),
                    resultSet.getInt("productItemId"),
                    resultSet.getInt("quantity"),
                    resultSet.getInt("itemTotal")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
}

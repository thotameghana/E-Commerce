package com.ecommerce.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Products;
import com.ecommerce.resources.MyConnection;

public class ProductDaoImpl implements ProductDao {
    
    static Connection con;
    static {
        con = MyConnection.connect();
    }
    
    private static final String ADD_PRODUCT = "INSERT INTO products(name, category, discount, imagePath) VALUES (?, ?, ?, ?)";
    private static final String FETCH_ALL_PRODUCTS = "SELECT * FROM products";
    private static final String FETCH_SPECIFIC_PRODUCT = "SELECT * FROM products WHERE productId=?";
    private static final String UPDATE_DISCOUNT = "UPDATE products SET discount=? WHERE productId=?";
    private static final String DELETE_PRODUCT = "DELETE FROM products WHERE productId=?";
    
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;

    @Override
    public int addProduct(Products p) {
        try {
            pstmt = con.prepareStatement(ADD_PRODUCT);
            pstmt.setString(1, p.getName());
            pstmt.setString(2, p.getCategory());
            pstmt.setString(3, p.getDiscount());
            pstmt.setString(4, p.getImagePath());
            
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ArrayList<Products> fetchAllProducts() {
    	ArrayList<Products> productList = new ArrayList<>();
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(FETCH_ALL_PRODUCTS);
            
            while (resultSet.next()) {
                productList.add(new Products(
                    resultSet.getInt("productId"),
                    resultSet.getString("name"),
                    resultSet.getString("category"),
                    resultSet.getString("discount"),
                    resultSet.getString("imagePath")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Products fetchSpecificProduct(int productId) {
        try {
            pstmt = con.prepareStatement(FETCH_SPECIFIC_PRODUCT);
            pstmt.setInt(1, productId);
            
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return new Products(
                    resultSet.getInt("productId"),
                    resultSet.getString("name"),
                    resultSet.getString("category"),
                    resultSet.getString("discount"),
                    resultSet.getString("imagePath")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(String discount, int productId) {
        try {
            pstmt = con.prepareStatement(UPDATE_DISCOUNT);
            pstmt.setString(1, discount);
            pstmt.setInt(2, productId);
            
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteProduct(int productId) {
        try {
            pstmt = con.prepareStatement(DELETE_PRODUCT);
            pstmt.setInt(1, productId);
            
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

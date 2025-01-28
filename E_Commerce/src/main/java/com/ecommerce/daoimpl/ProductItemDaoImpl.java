package com.ecommerce.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.dao.ProductItemDao;
import com.ecommerce.model.ProductItems;
import com.ecommerce.resources.MyConnection;

public class ProductItemDaoImpl implements ProductItemDao {

    static Connection con;
    static {
        con = MyConnection.connect();
    
    }

    private static final String ADD_PRODUCT_ITEM = "INSERT INTO productitems(productId, name, description, ratings, price, imagePath) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FETCH_ALL_PRODUCT_ITEMS = "SELECT * FROM productitems";
    private static final String FETCH_SPECIFIC_PRODUCT_ITEM = "SELECT * FROM productitems WHERE productItemId=?";
    private static final String UPDATE_PRODUCT_ITEM = "UPDATE productitems SET ratings=? WHERE productItemId=?";
    private static final String DELETE_PRODUCT_ITEM = "DELETE FROM productitems WHERE productItemId=?";
    
    private static final String FETCH_BY_PRODUCTID="select * from productitems where productId=?";
    
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;

    @Override
    public int addProductItem(ProductItems p) {
        try {
            pstmt = con.prepareStatement(ADD_PRODUCT_ITEM);
            pstmt.setInt(1, p.getProductId());
            pstmt.setString(2, p.getName());
            pstmt.setString(3, p.getDescription());
            pstmt.setFloat(4, p.getRatings());
            pstmt.setFloat(5, p.getPrice());
            pstmt.setString(6, p.getImagePath());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<ProductItems> fetchAllProductItems() {
        List<ProductItems> productItemList = new ArrayList<>();
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(FETCH_ALL_PRODUCT_ITEMS);

            while (resultSet.next()) {
                productItemList.add(new ProductItems(
                    resultSet.getInt("productItemId"),
                    resultSet.getInt("productId"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getFloat("ratings"),
                    resultSet.getInt("price"),
                    resultSet.getString("imagePath")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productItemList;
    }

    @Override
    public ProductItems fetchSpecificProductItems(int productItemId) {
        try {
            pstmt = con.prepareStatement(FETCH_SPECIFIC_PRODUCT_ITEM);
            pstmt.setInt(1, productItemId);

            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return new ProductItems(
                    resultSet.getInt("productItemId"),
                    resultSet.getInt("productId"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getFloat("ratings"),
                    resultSet.getInt("price"),
                    resultSet.getString("imagePath")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateProductItems(float ratings, int productItemId) {
        try {
            pstmt = con.prepareStatement(UPDATE_PRODUCT_ITEM);
            pstmt.setFloat(1, ratings);
            pstmt.setInt(2, productItemId);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteProductItems(int productItemId) {
        try {
            pstmt = con.prepareStatement(DELETE_PRODUCT_ITEM);
            pstmt.setInt(1, productItemId);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

	@Override
	public List<ProductItems> fetchByProductId(int productId) {
		List<ProductItems> productList=new ArrayList<>();
		try {
			pstmt=con.prepareStatement(FETCH_BY_PRODUCTID);
			pstmt.setInt(1, productId);
			resultSet=pstmt.executeQuery();
			while(resultSet.next()) {
				productList.add(
						new ProductItems(
							resultSet.getInt("productItemId"),
							resultSet.getInt("productId"),
							resultSet.getString("name"),
							resultSet.getString("description"),
							resultSet.getFloat("ratings"),
							resultSet.getInt("price"),
							resultSet.getString("imagePath")						
						));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}
	
}

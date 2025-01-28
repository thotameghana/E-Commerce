package com.ecommerce.dao;

import java.util.ArrayList;

import com.ecommerce.model.Products;

public interface ProductDao {
	int addProduct(Products p);
	ArrayList<Products> fetchAllProducts();
	Products fetchSpecificProduct(int productId);
	int update(String discount,int productId );
	int deleteProduct(int productId);
}	

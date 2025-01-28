package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.model.ProductItems;

public interface ProductItemDao {
	int addProductItem(ProductItems p);
	List<ProductItems> fetchAllProductItems();
	ProductItems fetchSpecificProductItems(int productItemId);
	int updateProductItems(float ratings,int productItemId);
	int deleteProductItems(int productItemId);
	List<ProductItems> fetchByProductId(int productId);
}

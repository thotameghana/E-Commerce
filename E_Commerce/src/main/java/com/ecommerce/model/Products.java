package com.ecommerce.model;

public class Products {
	private int productId;
	private String name;
	private String category;
	private String discount;
	private String imagePath;
	
	public Products() {
		// TODO Auto-generated constructor stub
	}
	
	public Products(int productId, String name, String category, String discount, String imagePath) {
		super();
		this.productId = productId;
		this.name = name;
		this.category = category;
		this.discount = discount;
		this.imagePath = imagePath;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return productId+"       "+name+"     "+category+"     "+discount+"    "+imagePath;
	}

}

package com.ecommerce.resources;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	static Connection con=null;
	public static Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e-commerce","root","root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(con!=null) {
			return con;
		}
		return con;
	}
	public void disconnect() {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

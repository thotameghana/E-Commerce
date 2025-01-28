package com.ecommerce.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.dao.UserDao;
import com.ecommerce.model.User;
import com.ecommerce.resources.MyConnection;

public class UserDaoImpl implements UserDao {
	
	static Connection con;
	static {
		con=MyConnection.connect();
	}
	private static final String ADDUSER="insert into user(userName,email,password,address) value(?,?,?,?)";
	private static final String FETCHALL="select * from user";
	private static final String FETCHONE="select * from user where email=?";
	private static final String UPDATE="update user set address=? where userId=?";
	private static final String DELETE="delete from user where userId=?";
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	static List<User> userList=new ArrayList<>();
	@Override
	public int addUser(User u) {
		try {
			pstmt=con.prepareStatement(ADDUSER);
			pstmt.setString(1, u.getUserName());
			pstmt.setString(2, u.getEmail());
			pstmt.setString(3, u.getPassword());
			pstmt.setString(4, u.getAddress());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<User> fetchAllUsers() {
		try {
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(FETCHALL);
			return fetchFromTable(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User fetchSpecificUser(String email) {
		try {
			pstmt=con.prepareStatement(FETCHONE);
			pstmt.setString(1, email);
			
			resultSet=pstmt.executeQuery();
			if(resultSet.next()) {
				return new User(
						resultSet.getString("userName"),
						resultSet.getString("email"),
						resultSet.getString("password"),
						resultSet.getString("address")
				);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateUser(String address,int userId) {
		try {
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, address);
			pstmt.setInt(2, userId);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteUser(int userId) {
		try {
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, userId);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	private List<User> fetchFromTable(ResultSet resultSet){
		userList.clear();
		
		try {
			while(resultSet.next()) {
				userList.add(new User(
						resultSet.getString("userName"),
						resultSet.getString("email"),
						resultSet.getString("password"),
						resultSet.getString("address")
					));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
}

package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.model.User;

public interface UserDao {
	int addUser(User u);
	List<User> fetchAllUsers();
	User fetchSpecificUser(String email);
	int updateUser(String address,int userId);
	int deleteUser(int userId);
}

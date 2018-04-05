package com.web.mapper;

import java.util.List;

import com.web.entity.Order;
import com.web.entity.User;

public interface UserMapper {

	public User GetUserByID(int id) throws Exception;
	public int insertUser(User user) throws Exception;
	public int deleteUser(int id) throws Exception;
	public int updateUser(int id) throws Exception;
	public User getAllUser() throws Exception;
	public List<Order> getUserOrders(int id) throws Exception;
	public User getUserById(int id) throws Exception;
}

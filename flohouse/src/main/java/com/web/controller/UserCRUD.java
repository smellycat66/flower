package com.web.controller;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;

import com.web.entity.User;

@Controller
public class UserCRUD {

	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
			reader = Resources.getResourceAsReader("mybatis.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}
	/**
	 * @param args
	 */
	public  static void main(String[] args) {
		// TODO Auto-generated method stub
		
		getUserById(1);
		
		User user = new User();
	    user.setName("John");
	    user.setPassword("123");
		insertUser(user);
		
		//deleteUser(32);
		
		updateUser(1,"ooo","333");
		getAllUser();
	}
	
	public static void getUserById(int id) {
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			User user = (User) session.selectOne(
					"com.web.mapper.UserMapper.GetUserByID", id);
			if(user!=null){
				String userInfo = "查询的是 "+user.getId()+" 号 姓名:"+user.getName()+" 密码:"+user.getPassword();
				System.out.println(userInfo);
			}
		} finally {
			session.close();
		}
		
	}
	
	public static void insertUser(User user) {
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			
		    session.insert("com.web.mapper.UserMapper.insertUser", user);
		    session.commit();
		    String userInfo = "新增用户  编号: "+user.getId()+" 姓名:"+user.getName()+" 密码:"+user.getPassword();
		    System.out.println(userInfo);
			
		} finally {
			session.close();
		}
		
	}
	
public static void deleteUser(int id) {
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			User user=(User) session.selectOne("com.web.mapper.UserMapper.GetUserByID", id);
		   session.delete("com.web.mapper.UserMapper.deleteUser", id);
		   session.commit();
		   String userInfo = "user no."+user.getId()+" name: "+user.getName()+" is deleted";
		   System.out.println(userInfo);	
		} finally {
			session.close();
		}
		
	}


public static void updateUser(int id,String name, String password) {
	
	SqlSession session = sqlSessionFactory.openSession();
	try {
		User user=new User();
		user.setId(id);
		user.setName(name);
		user.setPassword(password);
		
	   session.update("com.web.mapper.UserMapper.updateUser", user);
	   session.commit();
	   String userInfo="updated user no."+user.getId()+" new name:"+user.getName()+
			   " new password:"+user.getPassword();
	   System.out.println(userInfo);
	   
		
	} finally {
		session.close();
	}
	
}

public static void getAllUser() {
	
	SqlSession session = sqlSessionFactory.openSession();
	try {
		
	   List<User> userlist=session.selectList("com.web.mapper.UserMapper.getAllUser");
	  
	   System.out.println(userlist);
		
	} finally {
		session.close();
	}
	
}

}

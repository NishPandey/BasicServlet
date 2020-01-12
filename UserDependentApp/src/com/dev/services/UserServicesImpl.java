package com.dev.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.dev.beans.User;
import com.dev.dao.JDBCImpl;
import com.dev.dao.UserDAO;


public class UserServicesImpl implements UserServices {

	private UserDAO db = new JDBCImpl();
	
	@Override
	public Boolean createProfile(User user) {
		return db.createProfile(user);
	}

	@Override
	public User searchUser(Integer userId) {
		return db.searchUser(userId);
	}

	@Override
	public Boolean updatePassword(Integer userId, String oldPassword, String newPassword) {
		return db.updatePassword(userId, oldPassword, newPassword);
	}
	
	@Override
	public User login(Integer userId, String password) {
		return db.login(userId, password);
	}


	@Override
	public Boolean deleteUser(Integer userId, String password) {
		return db.deleteUser(userId, password);
	}
	
	@Override
	public String getRandomName() throws Exception {
		FileReader in = new FileReader("C:/Users/Nisha/Desktop/RandomName.txt");
		BufferedReader reader = new BufferedReader(in);
		
		String name = "";
		ArrayList<String> list = new ArrayList<>();
		
		while((name = reader.readLine()) != null) {
			list.add(name);
		}
		
		int randomIndex = (int) (Math.random() * list.size());
		
		reader.close();
		return list.get(randomIndex);
		
	}

}
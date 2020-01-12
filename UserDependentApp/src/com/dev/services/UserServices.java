
package com.dev.services;

import com.dev.beans.User;

public interface UserServices {
	
	public Boolean createProfile(User user);
	public User searchUser(Integer userId);
	public Boolean updatePassword(Integer userId, String oldPassword, String newPassword);
	public Boolean deleteUser(Integer userId, String password);
	public User login(Integer userId, String password);
	public String getRandomName() throws Exception;
	

}
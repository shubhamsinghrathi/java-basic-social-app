package com.indi.basicsocialapp.dao;

import java.util.List;

import com.indi.basicsocialapp.entity.SecUser;
import com.indi.basicsocialapp.entity.User;
import com.indi.basicsocialapp.model.SecuredUser;

public interface UserDao {

	public User addUser(User user);
	
	public User updateUser(User user);
	
	public User findUserByEmail(String email);
	
	public List<SecUser> getUserList(int pageNumber, int limit);
	
	public List<SecuredUser> getFollowerList(int userId, int pageNumber, int limit);
	
	public List<SecuredUser> getFollowingList(int userId, int pageNumber, int limit);

	public User findUserById(int id);

	public List<Integer> getFollowingIds(int userId);
	
}

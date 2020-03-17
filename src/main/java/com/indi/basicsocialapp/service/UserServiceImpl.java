package com.indi.basicsocialapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indi.basicsocialapp.dao.UserDao;
import com.indi.basicsocialapp.entity.SecUser;
import com.indi.basicsocialapp.entity.User;
import com.indi.basicsocialapp.model.SecuredUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public User addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	@Transactional
	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}
	
	@Override
	@Transactional
	public User findUserById(int id) {
		return userDao.findUserById(id);
	}

	@Override
	@Transactional
	public List<SecUser> getUserList(int pageNumber, int limit) {
		return userDao.getUserList(pageNumber, limit);
	}

	@Override
	@Transactional
	public List<SecuredUser> getFollowerList(int userId, int pageNumber, int limit) {
		return userDao.getFollowerList(userId, pageNumber, limit);
	}

	@Override
	@Transactional
	public List<SecuredUser> getFollowingList(int userId, int pageNumber, int limit) {
		return userDao.getFollowingList(userId, pageNumber, limit);
	}

	@Override
	public List<Integer> getFollowingIds(int userId) {
		return userDao.getFollowingIds(userId);
	}

}

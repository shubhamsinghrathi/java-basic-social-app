package com.indi.basicsocialapp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.indi.basicsocialapp.entity.SecUser;
import com.indi.basicsocialapp.entity.User;
import com.indi.basicsocialapp.entity.follows.Follow;
import com.indi.basicsocialapp.model.SecuredUser;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private EntityManager entityManager;
	
	public UserDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public User addUser(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		entityManager.merge(user);
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		Query usersQuery = entityManager.createQuery("from User where email=:email", User.class);
		usersQuery.setParameter("email", email);
		usersQuery.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<User> users = usersQuery.getResultList();
		return users.get(0);
	}

	@Override
	public User findUserById(int id) {
		return entityManager.find(User.class, id);
	}
	
	@Override
	public List<SecUser> getUserList(int pageNumber, int limit) {
		Query usersQuery = entityManager.createQuery("from SecUser", SecUser.class);
		usersQuery.setFirstResult((pageNumber - 1) * limit);
		usersQuery.setMaxResults(limit);
		
		@SuppressWarnings("unchecked")
		List<SecUser> users = usersQuery.getResultList();
		return users;
	}

	@Override
	public List<SecuredUser> getFollowerList(int userId, int pageNumber, int limit) {
		Query followsQuery = entityManager.createQuery("from Follow where userId=:userId", Follow.class);
		followsQuery.setParameter("userId", userId);
		followsQuery.setFirstResult((pageNumber - 1) * limit);
		followsQuery.setMaxResults(limit);
		
		@SuppressWarnings("unchecked")
		List<Follow> follows = followsQuery.getResultList();
		
		ArrayList<SecuredUser> users = new ArrayList<>();
		for (Follow follow : follows) {
			users.add(new SecuredUser(follow.getFollower()));
		}
		return users;
	}

	@Override
	public List<SecuredUser> getFollowingList(int userId, int pageNumber, int limit) {
		Query followsQuery = entityManager.createQuery("from Follow where followerId=:userId", Follow.class);
		followsQuery.setParameter("userId", userId);
		followsQuery.setFirstResult((pageNumber - 1) * limit);
		followsQuery.setMaxResults(limit);
		
		@SuppressWarnings("unchecked")
		List<Follow> follows = followsQuery.getResultList();
		
		ArrayList<SecuredUser> users = new ArrayList<>();
		for (Follow follow : follows) {
			users.add(new SecuredUser(follow.getFollower()));
		}
		return users;
	}

	@Override
	public List<Integer> getFollowingIds(int userId) {
		Query followingUserIdsQuery = entityManager.createQuery("select f.following.id from Follow f where followerId=:userId");
		followingUserIdsQuery.setParameter("userId", userId);
		
		@SuppressWarnings("unchecked")
		List<Integer> rows = followingUserIdsQuery.getResultList();
		
		return rows;
	}

}

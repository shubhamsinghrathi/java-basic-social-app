package com.indi.basicsocialapp.dao.follows;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.indi.basicsocialapp.entity.follows.Follow;

@Repository
public class FollowDaoImpl implements FollowDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Follow addFollow(Follow follow) {
		entityManager.persist(follow);
		return follow;
	}

	@Override
	public void removeFollow(int followId) {
		Query deleteQuery = entityManager.createQuery("delete from Follow where id=:followId");
		deleteQuery.setParameter("followId", followId);
		deleteQuery.executeUpdate();
	}

	@Override
	public void removeFollowByUserAndFollowerId(int userId, int followerId) {
		Query deleteQuery = entityManager.createQuery("delete from Follow where followerId=:followerId and userId=:userId");
		deleteQuery.setParameter("userId", userId);
		deleteQuery.setParameter("followerId", followerId);
		deleteQuery.executeUpdate();
	}

}

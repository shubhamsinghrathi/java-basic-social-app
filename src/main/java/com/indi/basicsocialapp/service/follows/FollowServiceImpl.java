package com.indi.basicsocialapp.service.follows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indi.basicsocialapp.dao.follows.FollowDao;
import com.indi.basicsocialapp.entity.follows.Follow;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowDao followDao;
	
	@Override
	@Transactional
	public Follow addFollow(Follow follow) {
		return followDao.addFollow(follow);
	}

	@Override
	@Transactional
	public void removeFollow(int id) {
		followDao.removeFollow(id);
		
	}

	@Override
	@Transactional
	public void removeFollowByUserAndFollowerId(int userId, int followerId) {
		followDao.removeFollowByUserAndFollowerId(userId, followerId);
	}

}

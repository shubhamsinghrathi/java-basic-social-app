package com.indi.basicsocialapp.dao.follows;

import com.indi.basicsocialapp.entity.follows.Follow;

public interface FollowDao {

	public Follow addFollow(Follow follow);

	public void removeFollow(int id);

	public void removeFollowByUserAndFollowerId(int userId, int followerId);
	
}

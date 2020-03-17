package com.indi.basicsocialapp.service.follows;

import com.indi.basicsocialapp.entity.follows.Follow;

public interface FollowService {

	public Follow addFollow(Follow follow);
	
	public void removeFollow(int id);
	
	public void removeFollowByUserAndFollowerId(int userId, int followerId);
	
}

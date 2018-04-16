package edu.northeastern.cs4500.user.friendRec;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieFriendRecRepository extends JpaRepository<MovieFriendRecObject, Integer>{

	@Async
	@Query
	(value = "SELECT m FROM MovieFriendRecommendation m WHERE user_id = ?1")
	public List<MovieFriendRecObject> getMFR(Integer user_id);
	
	@Async
	@Query
	(value = "SELECT friend_id FROM MovieFriendRecommendation WHERE user_id = ?1 AND movie_id = ?2")
	public List<Integer> getFriendsRecommenedTo(Integer user_id, Integer movie_id);
	
	@Async
	@Query
	(value = "SELECT movieRec FROM MovieFriendRecommendation movieRec WHERE user_id = ?1 AND movie_id = ?2")
	public List<MovieFriendRecObject> getRecMovie(Integer user_id, Integer movie_id);
	
}

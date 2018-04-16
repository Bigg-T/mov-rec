package edu.northeastern.cs4500.user.friendRec;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Movie Friend Recommendation
 * Users can recommend movies to friends, this table keeps track of the movies recommended
 */

@Entity(name="MovieFriendRecommendation")
public class MovieFriendRecObject {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int movie_id;
	private int user_id;
	private int friend_id;

	public MovieFriendRecObject() {
	}
	
	public MovieFriendRecObject(int user_id, int friend_id, int movie_id) {
		this.movie_id = movie_id;
		this.setUser_id(user_id);
		this.setFriend_id(friend_id);
	}

	public int getId() {
		return id;
    }
	
	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}
}

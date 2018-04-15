package edu.northeastern.cs4500.user.friendRec;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import edu.northeastern.cs4500.DB.user.UserObject;
import edu.northeastern.cs4500.JPARepositories.MovieRatingRepository;
import edu.northeastern.cs4500.JPARepositories.UserRepository;
import edu.northeastern.cs4500.models.MovieRatingsObject;

@Component
public class MovieFriendRecService {
	
	@Autowired
	MovieFriendRecRepository MFRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	MovieRatingRepository movieRepo;
	
	private final String SUCCESS = "isSuccess"; 
	private final String STATUS = "status";
	private final String MESSAGE = "message";


	/**
	 * User is able to recommend a movie to another friend
	 * NOTE: Need to add validation to see if the two users are friends
	 */
	public HashMap<String,Object> recommendMovie(int userId, int friendId, int movieId) {
		HashMap<String, Object> context = new HashMap<String, Object>();
		UserObject user = userRepo.getOne(userId);
		UserObject friend = userRepo.getOne(friendId);
		MovieRatingsObject movie = movieRepo.getOne(movieId);
		if (user == null || friend == null) {
			//error, one or more of the users does not exist
			context.put(SUCCESS, false);
			context.put(STATUS, HttpStatus.NOT_FOUND);
			context.put(MESSAGE, "Error: One of the users does not exist");
			return context;
		}
		
		if (movie == null) {
			//error, movie does not exist
			context.put(SUCCESS, false);
			context.put(STATUS, HttpStatus.NOT_FOUND);
			context.put(MESSAGE, "Error: The movie does not exist");
			return context;
		}
		
		MovieFriendRecObject rec = new MovieFriendRecObject(userId, friendId, movieId);
		MFRepo.save(rec);
		context.put(SUCCESS, true);
		context.put(STATUS, HttpStatus.OK);
		return context;
	}
	
	
	/**
	 * 
	 */
	public HashMap<String,Object> getFriendRecommendations(int userId) {
		HashMap<String, Object> context = new HashMap<String, Object>();
		UserObject user = userRepo.getOne(userId);
		if (user == null) {
			//error, user does not exist
			context.put(SUCCESS, false);
			context.put(STATUS, HttpStatus.NOT_FOUND);
			context.put(MESSAGE, "Error: Uers does not exist");
			return context;
		}
		//call a query that gets you all the movies recommended to you
		//Filter the data, if the same movie was recommended by multiple users
		//you don't want to display that multiple times
		
		return context;	
	}
	
	/**
	 * 
	 */
	public HashMap<String,Object> deleteFriendRecommendation(int userId, int movieId, int recId) {
		HashMap<String, Object> context = new HashMap<String, Object>();
		UserObject user = userRepo.getOne(userId);
		MovieRatingsObject movie = movieRepo.getOne(movieId);
		MovieFriendRecObject rec = MFRepo.getOne(recId); //should not have rec id but it'll be easier for now
		
		if (user == null) {
			//error, user does not exist
			context.put(SUCCESS, false);
			context.put(STATUS, HttpStatus.NOT_FOUND);
			context.put(MESSAGE, "Error: User does not exist");
			return context;
		}
		
		if (movie == null || rec == null) {
			context.put(SUCCESS, false);
			context.put(STATUS, HttpStatus.NOT_FOUND);
			context.put(MESSAGE, "Error: Movie was not found");
			//error, movie or rec does not exist
		}
		
		//easy: delete the recommendation
		//medium: make q query that gets all the recommendation based on userId and movieId then delete all of them
		return context;	
	}
	
	/**
	 * Gets user's whom you have not yet recommended a specific movie to
	 */
	public HashMap<String,Object> getFriendsToRecommend(int userId, int movieId) {
		HashMap<String, Object> context = new HashMap<String, Object>();
		UserObject user = userRepo.getOne(userId);
		MovieRatingsObject movie = movieRepo.getOne(movieId);

		if (user == null) {
			//error, user does not exist
			context.put(SUCCESS, false);
			context.put(STATUS, HttpStatus.NOT_FOUND);
			context.put(MESSAGE, "Error: Uers does not exist");
			return context;
		}
		
		if (movie == null) {
			//error, movie does not exist
		}
		
		//return all the friends that the user has not recommend the movie to
		//Note: Will require a table comparison.
		//First get all the users friends
		//then query the MovieFriendRec table by the movieId and the userId
		//filter out all of the friends that appear from the actual return
		return context;	
	}
	

}

package edu.northeastern.cs4500.user.friendRec;

import java.util.ArrayList;
import java.util.Collection;
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
	//	if (user == null || friend == null) {
			//error, one or more of the users does not exist
		try {
			user.getId();
			friend.getId();
		}catch(Exception e) {
			context.put(SUCCESS, false);
			context.put(STATUS, HttpStatus.NOT_FOUND);
			context.put(MESSAGE, "Error: One of the users does not exist");
			return context;
		}
		
		try {
			movie.getmovieId();
		} catch(Exception e) {
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
		context.put("recId", rec.getId());
		return context;
	}
	
	
	/**
	 * Gets the movies recommended to a given user
	 * @param user_id: The user who's recommended movies you're getting.
	 */
	public HashMap<String,Object> getFriendRecommendations(int user_id) {
		HashMap<String, Object> context = new HashMap<String, Object>();
		UserObject user = userRepo.getOne(user_id);
		try {
			user.getId();
		} catch(Exception e) {
			context.put(SUCCESS, false);
			context.put(STATUS, HttpStatus.NOT_FOUND);
			context.put(MESSAGE, "Error: Uers does not exist");
			return context;
		}
		
		
		List<MovieFriendRecObject> recMovies = MFRepo.getMFR(user_id);
		ArrayList<HashMap<String, Object>> allRecs = new ArrayList<>();
		ArrayList<Integer> recIds = new ArrayList<>();
		for (int i = 0; i < recMovies.size(); i++) {
			System.out.println("Inside the loop");
			//adds movies to the list
			MovieRatingsObject currentMovie = movieRepo.getOne(recMovies.get(i).getMovie_id());
			HashMap<String, Object> currentMovieHash = new HashMap<>();
			currentMovieHash.put("genres", currentMovie.getGenres());
			currentMovieHash.put("title", currentMovie.getTitle());
			currentMovieHash.put("movie_id", currentMovie.getmovieId());
			allRecs.add(currentMovieHash);
			recIds.add(recMovies.get(i).getId());
		}
		context.put(SUCCESS, true);
		context.put(STATUS, HttpStatus.OK);
		context.put("recIds", recMovies);
		context.put("size", allRecs.size());
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
		
		try {
			user.getId();
		} catch(Exception e) {
			context.put(SUCCESS, false);
			context.put(STATUS, HttpStatus.NOT_FOUND);
			context.put(MESSAGE, "Error: User does not exist");
			return context;
		}
		
		try {
			movie.getmovieId();
		    rec.getId();
		} catch(Exception e) {
			context.put(SUCCESS, false);
			context.put(STATUS, HttpStatus.NOT_FOUND);
			context.put(MESSAGE, "Error: Movie was not found");
			return context;
		}
		

		//easy: delete the recommendation
		MFRepo.delete(rec);
		context.put(SUCCESS, true);
		context.put(STATUS, HttpStatus.OK);
		//medium: make q query that gets all the recommendation based on userId and movieId then delete all of them
		return context;	
	}
	
	/**
	 * Gets user's whom you have not yet recommended a specific movie to
	 */
	public HashMap<String,Object> getFriendsToRecommend(int user_id, int movie_id) {
		HashMap<String, Object> context = new HashMap<String, Object>();
		UserObject user = userRepo.getOne(user_id);
		MovieRatingsObject movie = movieRepo.getOne(movie_id);

		try {
			user.getId();
		} catch(Exception e) {
			context.put(SUCCESS, false);
			context.put(STATUS, HttpStatus.NOT_FOUND);
			context.put(MESSAGE, "Error: Uers does not Exist");
			return context;
		}

		
		try {
			movie.getmovieId();
		}catch(Exception e) {
			context.put(SUCCESS, false);
			context.put(STATUS, HttpStatus.NOT_FOUND);
			context.put(MESSAGE, "Error: Movie Does Not Exist");
			return context;
		}
		
		List<Integer> friendIds = MFRepo.getFriendsRecommenedTo(user_id, movie_id);
		HashMap<Integer, Boolean> seenFriends = new HashMap<>();
		Collection<UserObject> userFriends =  user.getFriends(); 
		for (int j = 0; j < userFriends.size(); j++) {
			if (userFriends.iterator().hasNext()) {
				int id = userFriends.iterator().next().getId();
				seenFriends.put(id, true);
			}
		}
		
		
		List<UserObject> friends = new ArrayList<>();
		//the list of friends shown
		for (int i = 0; i < userFriends.size(); i++) {
			if (userFriends.iterator().hasNext()) {
				UserObject currentFriend = userFriends.iterator().next();
				if (seenFriends.get(currentFriend.getId())) {
					continue;
				} else {
					friends.add(currentFriend);
				}
			}
		}
		context.put(SUCCESS, true);
		context.put(STATUS, HttpStatus.OK);
		context.put("friends", friends);
		return context;
		
		//return all the friends that the user has not recommend the movie to
		//Note: Will require a table comparison.
		//First get all the users friends
		//then query the MovieFriendRec table by the movieId and the userId
		//filter out all of the friends that appear from the actual return
	}
	

}

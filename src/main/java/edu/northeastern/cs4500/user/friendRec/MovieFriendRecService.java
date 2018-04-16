package edu.northeastern.cs4500.user.friendRec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
	public HashMap<String,Object> recommendMovie(int userId, int friendId, int tmdbId) {
		HashMap<String, Object> context = new HashMap<String, Object>();
		UserObject user = userRepo.getOne(userId);
		UserObject friend = userRepo.getOne(friendId);
		//MovieRatingsObject movie = movieRepo.getOne(movieId);
		MovieRatingsObject movie;
		List<MovieRatingsObject> tmbdMovie = movieRepo.getMovieByTmdbId(tmdbId);
		try {
			movie = tmbdMovie.get(0);
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

		
		MovieFriendRecObject rec = new MovieFriendRecObject(userId, friendId, tmdbId);
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
		//ArrayList<Integer> allRecs = new ArrayList<>();
		ArrayList<Integer> tmdbIds = new ArrayList<>();
		for (int i = 0; i < recMovies.size(); i++) {
			//adds movies to the list
			//MovieRatingsObject currentMovie = movieRepo.getOne(recMovies.get(i).getMovie_id());
			//HashMap<String, Object> currentMovieHash = new HashMap<>();
			//currentMovieHash.put("tmdb_id", currentMovie.getTmdbId());
			//allRecs.add(currentMovie.getTmdbId());
			tmdbIds.add(recMovies.get(i).getMovie_id());
			
		}
		context.put(SUCCESS, true);
		context.put(STATUS, HttpStatus.OK);
		context.put("tmdb_ids", tmdbIds);
		return context;	
	}
	
	/**
	 * 
	 */
	public HashMap<String,Object> deleteFriendRecommendation(int userId, int tmdbId) {
		HashMap<String, Object> context = new HashMap<String, Object>();
		UserObject user = userRepo.getOne(userId);
		List<MovieRatingsObject> tmdbMovie = movieRepo.getMovieByTmdbId(tmdbId);
		MovieFriendRecObject rec;
		MovieRatingsObject movie;
		
		
		//MovieRatingsObject movie = movieRepo.getOne(movieId);
		//MovieFriendRecObject rec = MFRepo.getOne(recId); //should not have rec id but it'll be easier for now
		
		try {
			user.getId();
		} catch(Exception e) {
			context.put(SUCCESS, false);
			context.put(STATUS, HttpStatus.NOT_FOUND);
			context.put(MESSAGE, "Error: User does not exist");
			return context;
		}
		
		try {
			movie = tmdbMovie.get(0);
			System.out.println("IT IS ABLE TO GET A MOVIE");
			rec = MFRepo.getRecMovie(user.getId(), movie.getTmdbId()).get(0);
			System.out.println("IT IS NOT ABLE TO GET A RECOMMENDATION");
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
	public HashMap<String,Object> getFriendsToRecommend(int user_id, int tmdb_id) {
		HashMap<String, Object> context = new HashMap<String, Object>();
		UserObject user = userRepo.getOne(user_id);
		//MovieRatingsObject movie = movieRepo.getOne(movie_id);
		MovieRatingsObject movie;

		try {
			user.getId();
		} catch(Exception e) {
			context.put(SUCCESS, false);
			context.put(STATUS, HttpStatus.NOT_FOUND);
			context.put(MESSAGE, "Error: Uers does not Exist");
			return context;
		}
		
		try {
			movie = movieRepo.getMovieByTmdbId(tmdb_id).get(0);
			movie.getmovieId();
		} catch(Exception e) {
			context.put(SUCCESS, false);
			context.put(STATUS, HttpStatus.NOT_FOUND);
			context.put(MESSAGE, "Error: Movie Does Not Exist");
			return context;
		}

		//-----END VALIDATION
		
		List<Integer> friendIds = MFRepo.getFriendsRecommenedTo(user_id, tmdb_id);
		Collection<UserObject> userFriends =  user.getFriends(); //user's friends
		HashMap<Integer, Boolean> alreadyRecommended = new HashMap<>();

			
		
		//IF I see a friend ID in getFriendRecommened then I don't want to see them again
		for (int j = 0; j < friendIds.size(); j++) {
			alreadyRecommended.put(friendIds.get(j), true);
		}
		List<HashMap<String, Object>> allFriends = new ArrayList<>();
		
		
		//List<UserObject> friends = new ArrayList<>();
		//HashMap<String, Object> friend2 = new HashMap<String, Object>(); 
		System.out.println("--------BEFORE WEIRD ITERATOR THING-------");
		userFriends.forEach(currentFriend -> {
			if (alreadyRecommended.get(currentFriend.getId()) != null) {
				HashMap<String, Object> currentFriendHash = new HashMap<>();
				currentFriendHash.put("name", currentFriend.getFirst_name() + " " + currentFriend.getLast_name());
				currentFriendHash.put("id", currentFriend.id);
				allFriends.add(currentFriendHash);				
			}
		});
		context.put(SUCCESS, true);
		context.put(STATUS, HttpStatus.OK);
		context.put("friends", allFriends);
		return context;
		
		//return all the friends that the user has not recommend the movie to
		//Note: Will require a table comparison.
		//First get all the users friends
		//then query the MovieFriendRec table by the movieId and the userId
		//filter out all of the friends that appear from the actual return
	}
	

}

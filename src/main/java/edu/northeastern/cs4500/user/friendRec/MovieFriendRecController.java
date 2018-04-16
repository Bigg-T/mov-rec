package edu.northeastern.cs4500.user.friendRec;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs4500.DB.user.UserObject;

@CrossOrigin(origins = {"http://m0vi3h4ll.s3-website.us-east-2.amazonaws.com",
"http://localhost:3000"})
@RestController
public class MovieFriendRecController {
	
	@Autowired
	MovieFriendRecRepository MFCRepo;
	
	@Autowired
	MovieFriendRecService MFCService;
	
	MovieFriendRecController() {
		
	}
	
	/**
	 * A User can recommend a Movie to another User
	 */
	@PostMapping("/api/user/recommend/")
	@ResponseBody
	public HashMap<String,Object> recommendMovie(int userId, int friendId, int movieId) {
		return MFCService.recommendMovie(userId, friendId, movieId);
	}
	
	/**
	 * A user can see all the movies other users recommended to them
	 */
	@GetMapping("/api/user/recommend/getAll/")
	@ResponseBody
	public HashMap<String, Object> getFriendRecs(Integer userId) {
		return MFCService.getFriendRecommendations(userId);
	}
	
	/**
	 * A user can delete movies that were recommended to them by other users
	 */
	@PostMapping("/api/user/recommend/delete/")
	@ResponseBody
	public HashMap<String, Object> deleteFriendRec(int userId, int movieId) {
		return MFCService.deleteFriendRecommendation(userId, movieId);
	}
	
	/**
	 * The list of users a person can recommend a movie to
	 */
	@GetMapping("/api/user/recommend/get_friends/")
	@ResponseBody
	public HashMap<String, Object> getFriends(int userId, int movieId) {
		return MFCService.getFriendsToRecommend(userId, movieId);
	}
	
}

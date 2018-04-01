package edu.northeastern.cs4500.DB.user;

import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@CrossOrigin(origins = "http://m0vi3h4ll.s3-website.us-east-2.amazonaws.com")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@Controller
public class UserFriendController {
	
	public UserFriendController() {
		
	}
	
	@Autowired
	UserService userService;
	

	/**
	 * Adds a friend to a user
	 */
	@PostMapping("/api/user/add_friend/")
	@ResponseBody
	public HashMap<String,Object> addFriend(int userId, int friendId) {
		return userService.addFriend(userId, friendId);	
	}
	
	/**
	 * Removes a friend from a user's friends list
	 */
	@PostMapping("/api/user/remove_friend/")
	@ResponseBody
	public HashMap<String,Object> removeFriend(int userId, int friendId) {
		return userService.removeFriend(userId, friendId);
	}
}


package edu.northeastern.cs4500.DB.user;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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

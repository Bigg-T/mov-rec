package edu.northeastern.cs4500.DB.user;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class UserFriendController {
	
	public UserFriendController() {
		
	}
	
	@Autowired
	UserService userService;
	

	@GetMapping("/api/user/add_friend/")
	public Map<String,Boolean> addFriend(int userId, int requestedFriendId) {
		if (userService.userExists(userId) && userService.userExists(userId)) {
			return userService.addFriend(userId, requestedFriendId);
		} else {
			return Collections.singletonMap("isSuccess", false);
		}		
	}
	
	@GetMapping("/api/user/remove_friend/")
	public Map<String,Boolean> removeFriend(int userId, int friendId) {
		if (userService.userExists(userId) && userService.userExists(friendId)) {
			return userService.removeFriend(userId, friendId);
		} else {
			return Collections.singletonMap("isSuccess", false);
		}
	}
}

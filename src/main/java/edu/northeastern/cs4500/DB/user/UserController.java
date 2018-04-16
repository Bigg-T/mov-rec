package edu.northeastern.cs4500.DB.user;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import edu.northeastern.cs4500.JPARepositories.UserRepository;

//@CrossOrigin(origins = "loca")
//@CrossOrigin(origin = http://localhost:3000/)
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = {"http://m0vi3h4ll.s3-website.us-east-2.amazonaws.com",
		"http://localhost:3000"})
@RestController
public class UserController {

	//Constructor for testing purposes
	public UserController() {

	}

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepo;

	/**
	 * Returns every user in the Database
	 */
	@GetMapping("/api/user/all_users")
	@ResponseBody
	public List<UserObject> selectAllUserObjects() {
		return userService.selectAllUserObjects();
	}

	/**
	 * Validates for correct username and password for a User
	 */
	@GetMapping("/api/user/validate_login")
	@ResponseBody
	public HashMap<String,Object> validateLogin(String username, String pw) {
		return userService.validateLogin(username, pw);
	}

	/**
	 * Validates for correct User Id before logout
	 * @param user_request: The logged in user's request
	 */
	@GetMapping("/api/user/validate_logout")
	@ResponseBody
	public HashMap<String, Object> validateLogout(Integer user_request) {
		return userService.validateLogout(user_request);
	}

	/**
	 * Adds a user if non-duplicate username and email are given
	 */
	@GetMapping("/api/user/add_User/")
	@ResponseBody
	public HashMap<String,Object> addUser(String fname, String lname, String email, String pw, String username) {
		return userService.addUser(fname, lname, email, pw, username);
	}
	
	  @GetMapping("/api/delete/user")
	  @ResponseBody
	  public Map<String, Object> deleteUser(Integer user_id) {
		  return userService.deleteUser(user_id);
	  }
}

package edu.northeastern.cs4500.DB.user;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@Controller
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
	 * Adds a user if non-duplicate username and email are given
	 */
	@GetMapping("/api/user/add_User/")
	@ResponseBody
	public HashMap<String,Object> addUser(String fname, String lname, String email, String pw, String username) {
		return userService.addUser(fname, lname, email, pw, username);
	}
}

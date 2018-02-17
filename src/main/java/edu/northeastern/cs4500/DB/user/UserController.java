package edu.northeastern.cs4500.DB.user;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
	
	//Constructor for testing purposes
	public UserController() {
		
	}
	
	@Autowired
	UserService userService;
	
	/**
	 * I guess we can currently send through URL, but we should really be sending
	 * get requests
	 * @return
	 */
	
	@RequestMapping("/api/user/create")
	public UserObject createUser() {
		//UserObject obj = new UserObject("Jean Paul", "Torre", "torre.j@husky.neu.edu", "123456789", "jeanpaulrt");
		//UserRepository.save(obj);
		//return obj;
		return new UserObject();
	}
	
	@RequestMapping("/api/user/all_users")
	public List<UserObject> selectAllUserObjects() {
		return userService.selectAllUserObjects();
	}
	
	@RequestMapping("/api/user/validate_login")
	public Map<String,Boolean> validateLogin(String username, String pw) {
		return userService.validateLogin(username, pw);
	}

	//NOTE: This should not me a GET mapping it should be a POST mapping
	//Returns true or false depending on whether or not the user was added
	@GetMapping("/api/user/add_User/")
	public Map<String,Boolean> addUser(String fname, String lname, String email, String pw, String username) {
		return userService.addUser(fname, lname, email, pw, username);
	}
	
}

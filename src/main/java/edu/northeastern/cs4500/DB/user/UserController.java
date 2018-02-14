package edu.northeastern.cs4500.DB.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	UserRepository UserRepository;
	
	/**
	 * I guess we can currently send through URL, but we should really be sending
	 * get requests
	 * @return
	 */
	@RequestMapping("/api/user/create")
	public UserObject createUser() {
		UserObject obj = new UserObject("Jean Paul", "Torre", "torre.j@husky.neu.edu", "123456789", "jeanpaulrt");
		UserRepository.save(obj);
		return obj;
	}
	
	@RequestMapping("/api/user/all_users")
	public List<UserObject> selectAllUserObjects() {
		return UserRepository.findAll();
	}
	
	public boolean validateLogin(String username, String pw) {
		return false;
	}
	
	@RequestMapping("/api/user/add_User")
	public void addUser(String fname, String lname, String email, String pw, String username) {
		
	}
	
	private boolean userExists(String username) {
		return false;
	}

}

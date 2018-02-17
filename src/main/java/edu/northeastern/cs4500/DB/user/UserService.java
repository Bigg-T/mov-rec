package edu.northeastern.cs4500.DB.user;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component 
public class UserService {
	
	@Autowired
	UserRepository UserRepository;
	
	public List<UserObject> selectAllUserObjects() {
		return UserRepository.findAll();
	}
	
	public Map<String,Boolean> validateLogin(String username, String pw) {
		List<UserObject> allUsers = UserRepository.findAll();
		for (int i = 0; i < allUsers.size(); i++) {
			UserObject curUser = allUsers.get(i);
			if (curUser.getUsername().equals(username)) {
				if (curUser.getPassword().equals(pw)) { 
					return Collections.singletonMap("isSuccess", true);
				} 
			}
		}
		return Collections.singletonMap("isSuccess", false);
	}
	
	public Map<String, Boolean> addUser(String fname, String lname, String email, String pw, String username) {
		if (!usernameExists(username)) {
			if (!emailExists(email)) {
				UserObject obj = new UserObject(fname, lname, email, pw, username);
				try {
					UserRepository.save(obj);
					return Collections.singletonMap("isSuccess", true);
				} catch (Exception e) {
					return Collections.singletonMap("isSuccess", false);
				}
			}
		}
		
		return Collections.singletonMap("isSuccess", false);
	}
	
	private boolean usernameExists(String username) {
		List<UserObject> allUsers = UserRepository.findAll();
		for (int i = 0; i < allUsers.size(); i++) {
			UserObject curUser = allUsers.get(i);
			if (curUser.getUsername() == username) {
				return true;
			}
		}
		return false;
	}
	
	private boolean emailExists(String email) {
		List<UserObject> allUsers = UserRepository.findAll();
		for (int i = 0; i < allUsers.size(); i++) {
			UserObject curUser = allUsers.get(i);
			if (curUser.getEmail() == email) {
				return true;
			}
		}
		return false;
	}
}

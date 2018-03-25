package edu.northeastern.cs4500.DB.user;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Methods used by User controller
 */
@Component 
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<UserObject> selectAllUserObjects() {
		return userRepository.findAll();
	}
	
	/**
	 * Checks to see if a username and password match any User in the database
	 * @param username
	 * @param pw
	 * @return isSuccess boolean; HTTP status code
	 */
	public HashMap<String,Object> validateLogin(String username, String pw) {
		List<UserObject> allUsers = userRepository.findAll();
		HashMap<String, Object> context = new HashMap<String, Object>();
		for (int i = 0; i < allUsers.size(); i++) {
			UserObject curUser = allUsers.get(i);
			if (curUser.getUsername().equals(username)) {
				if (curUser.getPassword().equals(pw)) { 
					context.put("isSuccess", true);
					context.put("status", HttpStatus.OK);
					return context;
				} 
			}
		}
		context.put("isSuccess", false);
		context.put("status", HttpStatus.NOT_FOUND);
		return context;	
	}
	
	/**
	 * Adds a user to our database if the user doesn't already exist
	 * @param fname: first name
	 * @param lname: last name
	 * @param email: email (UNIQUE)
	 * @param pw: password
	 * @param username: username (UNIQUE)
	 * @return The id of the created user if there is one, an HTTP status of the request, and isSuccess boolean: true if user was 
	 * able to be added.
	 */
	public HashMap<String, Object> addUser(String fname, String lname, String email, String pw, String username) {
		HashMap<String, Object> context = new HashMap<String, Object>();  
		if (!usernameExists(username)) {
			if (!emailExists(email)) {
				UserObject obj = new UserObject(fname, lname, email, pw, username);			
				try {
					userRepository.save(obj);
					context.put("id", obj.getId());
					context.put("status", HttpStatus.OK);
					context.put("isSuccess", true);
					return context; 
				} catch (Exception e) {
					context.put("id", null);
					context.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
					context.put("isSuccess", false);
					return context;
				}
			}
		}
		context.put("id", null);
		context.put("status", HttpStatus.BAD_REQUEST);
		context.put("isSuccess", false);
		return context;
	}
	
	/**
	 * Checks to see if a username is tied to any User in the database
	 * @param username: the email being looked up
	 * @return true if the username is tied to a user; false otherwise
	 */
	private boolean usernameExists(String username) {
		List<UserObject> allUsers = userRepository.findAll();
		for (int i = 0; i < allUsers.size(); i++) {
			UserObject curUser = allUsers.get(i);
			if (curUser.getUsername() == username) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks to see if an email exists in our database
	 * @param email: the email being looked up
	 * @return true if the email is tied to a user; false otherwise
	 */
	private boolean emailExists(String email) {
	
		List<UserObject> allUsers = userRepository.findAll();
		System.out.println("after GET ALL USER");
		for (int i = 0; i < allUsers.size(); i++) {
			UserObject curUser = allUsers.get(i);
			if (curUser.getEmail() == email) {
				return true;
			}
		}
		return false;
	}
	

	/**
	 * Adds a friend if friend exists
	 * @param userId
	 * @param friendRequestedId
	 * @return true if friend was successfully added to user; false otherwise
	 */
	public Map<String, Boolean> addFriend(int userId, int friendRequestedId) {
		UserObject user = userRepository.getOne(userId);
		UserObject requestedFriend = userRepository.getOne(friendRequestedId);
		try {
			Collection<UserObject> userFriends = user.getFriends();
			userFriends.add(requestedFriend);
			userRepository.save(user);
			return Collections.singletonMap("isSuccess", true);
		} catch (Exception e) {
			return Collections.singletonMap("isSuccess", false);
		}
	}
	
	
	/**
	 * Checks if a user exists of not
	 * @param userId
	 * @return true if user exists; false otherwise
	 */
	protected boolean userExists(int userId) {
		UserObject user = userRepository.getOne(userId);
		if (user != null) {
			return true;
		} 
		return false;
	}
	
	/**
	 * Removes a friend 
	 * @param userId
	 * @param removedFriendId
	 * @return true if friend was successfully removed from user; false otherwise
	 */
	public Map<String, Boolean> removeFriend(int userId, int removedFriendId) {
		UserObject user = userRepository.getOne(userId);
		UserObject removedFriend = userRepository.getOne(removedFriendId);
		try {
			Collection<UserObject> userFriends = user.getFriends();
			userFriends.remove(removedFriend);
			userRepository.save(user);
			return Collections.singletonMap("isSuccess", true);
		} catch (Exception e) {
			return Collections.singletonMap("isSuccess", false);
		}
	}
}

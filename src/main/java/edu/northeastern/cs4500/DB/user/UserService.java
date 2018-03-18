package edu.northeastern.cs4500.DB.user;

import java.util.Collection;
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
	

	/**
	 * Adds a friend if friend exists
	 * @param userId
	 * @param friendRequestedId
	 * @return true if friend was successfully added to user; false otherwise
	 */
	public Map<String, Boolean> addFriend(int userId, int friendRequestedId) {
		UserObject user = UserRepository.getOne(userId);
		UserObject requestedFriend = UserRepository.getOne(friendRequestedId);
		try {
			Collection<UserObject> userFriends = user.getFriends();
			userFriends.add(requestedFriend);
			UserRepository.save(user);
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
		UserObject user = UserRepository.getOne(userId);
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
		UserObject user = UserRepository.getOne(userId);
		UserObject removedFriend = UserRepository.getOne(removedFriendId);
		try {
			Collection<UserObject> userFriends = user.getFriends();
			userFriends.remove(removedFriend);
			UserRepository.save(user);
			return Collections.singletonMap("isSuccess", true);
		} catch (Exception e) {
			return Collections.singletonMap("isSuccess", false);
		}
	}
}

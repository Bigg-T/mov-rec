package edu.northeastern.cs4500.DB.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import edu.northeastern.cs4500.JPARepositories.UserRepository;

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
		System.out.println("VALIDATE LOGIN METHOD");
		List<UserObject> allUsers = userRepository.findAll();
		HashMap<String, Object> context = new HashMap<String, Object>();
		for (int i = 0; i < allUsers.size(); i++) {
			UserObject curUser = allUsers.get(i);
			if (curUser.getUsername().equals(username)) {
				if (curUser.getPassword().equals(pw)) { 
					context.put("isSuccess", true);
					context.put("status", HttpStatus.OK);
					context.put("username", username);
					context.put("user_id", curUser.getId());
					//System.out.println(curUser.isLogged());
					curUser.setLogged(true);
					userRepository.save(curUser);
					//System.out.println(curUser.isLogged());
					//System.out.println("-------------------");
					return context;
				} 
			}
		}
		context.put("isSuccess", false);
		context.put("status", HttpStatus.NOT_FOUND);
		return context;	
	}
	
	/**
	 * Logs out the user
	 * @param user_request: user id
	 * @return
	 */
	public HashMap<String, Object> validateLogout(Integer user_request) {
		UserObject user = userRepository.getOne(user_request);
		HashMap<String, Object> context = new HashMap<String, Object>();
		try {
			if (!user.isLogged()) {
				context.put("isSuccess", false);
				context.put("message", "User not logged in");
				context.put("status", HttpStatus.BAD_REQUEST);

			} else {
				user.setLogged(false);
				userRepository.save(user);
				context.put("isSuccess", true);
				context.put("status", HttpStatus.OK);
				context.put("isLogged", user.isLogged());
			}
		} catch(Exception e) {
			context.put("message", "User does not exist");
			context.put("status", HttpStatus.NOT_FOUND);
			context.put("isSuccess", false);
		}
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
				obj.setLogged(true);
				try {
					userRepository.save(obj);
					context.put("id", obj.getId());
					context.put("status", HttpStatus.OK);
					context.put("isSuccess", true);
					context.put("user_id", obj.getId());
					return context; 
				} catch (Exception e) {
					context.put("id", null);
					context.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
					context.put("isSuccess", false);
					return context;
				}
			}
		}
//		context.put("id", null);
//		context.put("status", HttpStatus.BAD_REQUEST);
//		context.put("isSuccess", false);
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
	//edge cases: Friend already in friends list
	public HashMap<String, Object> addFriend(int userId, int friendRequestedId) {
		HashMap<String, Object> context = new HashMap<String, Object>();

		if (!userExists(userId)) {
			System.out.println("USER DOES NOT EXIST");
			context.put("isSuccess", false);
			context.put("status", HttpStatus.NOT_FOUND);
			context.put("message", "User does not exist");
			return context;
		}
		
		if (!userExists(friendRequestedId)) {
			System.out.println("FRIEND DOES NOT EXIST");
			context.put("isSuccess", false);
			context.put("status", HttpStatus.NOT_FOUND);
			context.put("message", "Friend Requested does not exist");
			return context;
		}
		
		if (userId == friendRequestedId) {
			context.put("isSuccess", false);
			context.put("status", HttpStatus.BAD_REQUEST);
			context.put("message", "User cannot add itself");
			return context;
		}
		
		UserObject user = userRepository.getOne(userId);
		UserObject requestedFriend = userRepository.getOne(friendRequestedId);
		
		if (user.isLogged() != true) {
			context.put("isSuccess", false);
			context.put("status", HttpStatus.BAD_REQUEST);
			context.put("message", "User Not Logged In");
			return context;
		}
		Collection<UserObject> userFriends = user.getFriends();
		boolean alreadyFriends = userFriends.contains(requestedFriend);

		if (alreadyFriends) {
			context.put("isSuccess", false);
			context.put("status", HttpStatus.CONFLICT);
			context.put("message", "Already Friends");
			return context;
		}

		userFriends.add(requestedFriend);
		userRepository.save(user);
		context.put("isSuccess", true);
		context.put("status", HttpStatus.OK);
		return context;
	}
	
	/**
	 * Checks if a user exists of not
	 * @param userId
	 * @return true if user exists; false otherwise
	 */
	protected boolean userExists(int userId) {
		UserObject user;
		try {
			user = userRepository.getOne(userId);
			user.getEmail();
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Removes a friend 
	 * @param userId
	 * @param removedFriendId
	 * @return true if friend was successfully removed from user; false otherwise
	 */
	public HashMap<String, Object> removeFriend(int userId, int friendId) {
		HashMap<String, Object> context = new HashMap<String, Object>();
		if (!userExists(userId)) {
			context.put("isSuccess", false);
			context.put("status", HttpStatus.NOT_FOUND);
			context.put("message", "User does not exist");
			return context;
		}
		
		if (!userExists(friendId)) {
			context.put("isSuccess", false);
			context.put("status", HttpStatus.NOT_FOUND);
			context.put("message", "Friend does not exist");
			return context;
		}		
		
		if (userId == friendId) {
			context.put("isSuccess", false);
			context.put("status", HttpStatus.BAD_REQUEST);
			context.put("message", "User cannot remove itself");
			return context;
		}
		
		UserObject user = userRepository.getOne(userId);
		UserObject removedFriend = userRepository.getOne(friendId);
				
		Collection<UserObject> userFriends = user.getFriends();
		if (!userFriends.contains(removedFriend)) {
			context.put("isSuccess", false);
			context.put("status", HttpStatus.BAD_REQUEST);
			context.put("message", "User's are not friends");
			return context;
		} else {
			userFriends.remove(removedFriend);
			userRepository.save(user);
			context.put("isSuccess", true);
			context.put("status", HttpStatus.OK);
			return context;
		}

	}
	
	/**
	 * Deletes all the user to user relations. Should be used before deleting a user's account
	 */
	public HashMap<String, Object> removeAllFriends(Integer user_id) {
		HashMap<String, Object> context = new HashMap<>();
		UserObject user = userRepository.getOne(user_id); 
	    try {
			Collection<UserObject> userFriends = user.getFriends();
			Iterator<UserObject> userFriendsList = userFriends.iterator();
			while (userFriends.size() > 0) {
				UserObject currentFriend = userFriendsList.next(); 
				userFriends.remove(currentFriend);
			}
			userRepository.save(user);
			context.put("isSuccess", true);
	    } catch(Exception e) {
	    		context.put("isSuccess", false);
	    }		
	    return context;
	}
	
	/**
	 * Deletes a users account
	 * @param user_id the user's id
	 */
	public HashMap<String, Object> deleteUser(Integer user_id) {
		HashMap<String, Object> context = new HashMap<>();
		UserObject user = userRepository.getOne(user_id);
		try {
			user.getId();	
			removeAllFriends(user_id);
		} catch (Exception e) {
			context.put("isSuccess", false);
			context.put("status", HttpStatus.NOT_FOUND);
			context.put("message", "user not found");
			return context;
		}
		userRepository.delete(user);
		context.put("isSuccess", true);
		context.put("status", HttpStatus.OK);
		return context;
	}
}

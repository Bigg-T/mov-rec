package edu.northeastern.cs4500.DB.user;



import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.stereotype.Controller;

@CrossOrigin(origins = "http://m0vi3h4ll.s3-website.us-east-2.amazonaws.com")
@Controller
public class UserProfile {

	public UserProfile() {
		
	}
	
	@Autowired
	UserRepository userRepository;

	/**
	 * Gets the User Data needed for the profile page	
	 * @param userId
	 * @return
	 */
	@GetMapping("/api/user/profile/")
	@ResponseBody
	public HashMap<String, Object> getUserData(Integer id, Integer user_request) {
		System.out.println("************************");
		System.out.println(id);
		System.out.println(user_request);
		System.out.println(id.compareTo(user_request) == 0);
		System.out.println("************************");
		List<UserObject> result = userRepository.getUserProfileData(id);
		HashMap<String, Object> userData = new HashMap<String, Object>();
		Boolean isLogged = false;
		if (id == null || user_request == null) {
			userData.put("isSuccess", false);
			userData.put("message", "Incorrect Input");
			userData.put("status", HttpStatus.BAD_REQUEST);
			return userData;
		}
		UserObject userLogged;
		try {
			userLogged = userRepository.getOne(user_request);
			isLogged = userLogged.isLogged();
		} catch(Exception e) {
			userData.put("isSuccess", false);
		userData.put("message", "User Does Not Exist");
		//I should probably redirect to an error page instead 
		userData.put("status", HttpStatus.NOT_FOUND);
		return userData;
		}
		if (isLogged) {
			if (result.size() > 0) {
				UserObject user = result.get(0);
				userData.put("about_me", user.getAbout_me());
				userData.put("profile_picture", user.getProf_pic());
				userData.put("first_name", user.getFirst_name());
				userData.put("last_name", user.getLast_name());
				userData.put("isSuccess", true);
				userData.put("status", HttpStatus.OK);
				userData.put("userId", id);
				if (id.compareTo(user_request) == 0) {
					userData.put("isFriend", false);
					userData.put("myProfile", true);
				} else {
					userData.put("myProfile", false);
					if (userLogged.getFriends().contains(user)) {
						userData.put("isFriend", true);
					}
					else {
						userData.put("isFriend", false);	
					}
				}
					//user.getFriends().contains(o)
			    } else {
				userData.put("isSuccess", false);
				userData.put("status", HttpStatus.NOT_FOUND);	
			}
			return userData;
		} else {
			//I should probably redirect to an error page instead 
			userData.put("isSuccess", false);
			userData.put("message", "User Not Logged In");
			userData.put("status", HttpStatus.BAD_REQUEST);
				return userData;
			}
	}
	
	
	/**
	 * Updates the user profile
	 * @param user_request
	 * @param about_me
	 * @param first_name
	 * @param last_name
	 * @return
	 */
	@PostMapping("/api/user/profile/edit")
	@ResponseBody
	public HashMap<String, Object> editUserData(Integer user_request, String about_me, String first_name,
			String last_name) {
		UserObject user;
		HashMap<String, Object> context = new HashMap<String, Object>();
		try {
			user = userRepository.getOne(user_request);
			if (user.isLogged()) {
			user.setAbout_me(about_me);
			user.setFirst_name(first_name);
			user.setLast_name(last_name);
			userRepository.save(user);
			context.put("isSuccess", true);
			context.put("status", HttpStatus.OK);
			context.put("message", "Succesfully Updated Profile");
			} else {
				context.put("isSuccess", false);
				context.put("status", HttpStatus.BAD_REQUEST);
				context.put("message", "User Not Logged In");
			}	
		} catch (Exception e) {
			context.put("isSuccess", false);
			context.put("status", HttpStatus.BAD_REQUEST);
			context.put("message", "User Does Not Exist");
		}
		return context;
	}
	
	
	/**
	 * Gets the User Data needed for the profile page	
	 * @param userId
	 * @return
	 */
	@GetMapping("/api/user/profile/edit")
	@ResponseBody
	public HashMap<String, Object> getUserEditData(Integer user_request) {
		UserObject user;
		HashMap<String, Object> context = new HashMap<String, Object>();
		try {
			user = userRepository.getOne(user_request);
			if (user.isLogged()) {
			context.put("about_me", user.getAbout_me());
			context.put("first_name", user.getFirst_name());
			context.put("last_name", user.getLast_name());
			context.put("isSuccess", true);
			context.put("status", HttpStatus.OK);
			} else {
				context.put("isSuccess", false);
				context.put("status", HttpStatus.BAD_REQUEST);
				context.put("message", "User Not Logged In");
			}	
		} catch (Exception e) {
			context.put("isSuccess", false);
			context.put("status", HttpStatus.BAD_REQUEST);
			context.put("message", "User Does Not Exist");
		}
		
		return context;
	}
}

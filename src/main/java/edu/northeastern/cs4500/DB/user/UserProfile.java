package edu.northeastern.cs4500.DB.user;



import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.stereotype.Controller;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
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
				if (id == user_request) {
					userData.put("isFriend", false);
				} else {
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
}

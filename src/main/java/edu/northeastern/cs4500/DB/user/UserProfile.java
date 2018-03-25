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
public HashMap<String, Object> getUserData(Integer id) {
	List<UserObject> result = userRepository.getUserProfileData(id);
	HashMap<String, Object> userData = new HashMap<String, Object>();

	if (result.size() > 0) {
		UserObject user = result.get(0);
		userData.put("about_me", user.getAbout_me());
		userData.put("profile_picture", user.getProf_pic());
		userData.put("first_name", user.getFirst_name());
		userData.put("last_name", user.getLast_name());
		userData.put("isSuccess", true);
		userData.put("status", HttpStatus.OK);	
		} else {
		userData.put("isSuccess", false);
		userData.put("status", HttpStatus.NOT_FOUND);	
	}
	return userData;
	}
}

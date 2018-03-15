package edu.northeastern.cs4500.DB.user;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class UserProfile {

	public UserProfile() {
		
	}
	
	@Autowired
	UserRepository userRepository;
	

@GetMapping("/api/user/profile/")
public Map<String,Boolean> getUserData(Integer userId) {
	Integer test = 1;
	System.out.println("SHOULD PRINT OUT THE USER INFORMATION");
	List<Object> result = userRepository.getUserProfileData(test);
    System.out.println(result.get(0));
    
	return null;
}


}

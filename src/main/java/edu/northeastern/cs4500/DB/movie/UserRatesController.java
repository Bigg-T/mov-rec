package edu.northeastern.cs4500.DB.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.northeastern.cs4500.DB.movie.UserRatesObject;
import edu.northeastern.cs4500.DB.user.UserObject;
import edu.northeastern.cs4500.JPARepositories.UserRatesRepository;
import edu.northeastern.cs4500.JPARepositories.UserRepository;

@RestController
public class UserRatesController {

    @Autowired
    UserRatesRepository userRatesRepository;
    
    @Autowired
    UserRepository userRepository; 

    @GetMapping("/api/movie/addUserRates/")
    public Map<String, Object> addUserRates(Integer movie_id, Integer user_id, Integer rate) {
      //check if user is logged in and exists
      HashMap<String, Object> userRatesData = new HashMap<String, Object>();
      Boolean isLoggedIn = false;
      if (movie_id == null || user_id == null || rate == null) {
        userRatesData.put("isSuccess", false);
        userRatesData.put("message", "Incorrect Input");
        userRatesData.put("status", HttpStatus.BAD_REQUEST);
        return userRatesData;
      }
      UserObject user;
      try {
        user = userRepository.getOne(user_id);
        isLoggedIn = user.isLogged();
      } catch(Exception e) {
        userRatesData.put("isSuccess", false);
        userRatesData.put("message", "User Does Not Exist");
        userRatesData.put("status", HttpStatus.NOT_FOUND);
        return userRatesData;
      }
      
      if(isLoggedIn) {
      try{
        UserRatesObject obj = new UserRatesObject(movie_id, user_id);
        obj.setRate(rate);
        userRatesRepository.save(obj);
        userRatesData.put("isSuccess", true);
	    userRatesData.put("status", HttpStatus.OK);
	    userRatesData.put("userRatingId", obj.getId());
	  	return userRatesData;
        }
      catch(Exception e) {
    	    userRatesData.put("isSuccess", false);
    	    userRatesData.put("message", e.getMessage());//"Error creating User Rates Object");
    	    userRatesData.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
    	  	return userRatesData;
      }
    }
    else {
		//I should probably redirect to an error page instead 
		userRatesData.put("isSuccess", false);
		userRatesData.put("message", "User Not Logged In");
		userRatesData.put("status", HttpStatus.BAD_REQUEST);
			return userRatesData;
		}
    }

    @RequestMapping("/api/movie/getUserRates/")
    public List<UserRatesObject> getTopMovies(Integer user_id) {
      List<UserRatesObject> userTopMovies = userRatesRepository.findByUserId(user_id);
      return userTopMovies;
    }
  }
package edu.northeastern.cs4500.DB.movie;
import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
/**
 * Created by amrita on 2/19/18.
 *
 */
import edu.northeastern.cs4500.DB.movie.TopMoviesObject;
import edu.northeastern.cs4500.DB.user.UserObject;
import edu.northeastern.cs4500.JPARepositories.TopMoviesRepository;
import edu.northeastern.cs4500.JPARepositories.UserRepository;
@RestController
public class TopMoviesController {
  @Autowired
  TopMoviesRepository topMoviesRepository;
  @Autowired 
  UserRepository userRepository;
  
  @GetMapping("/api/movie/addTopMovie/")
  @ResponseBody	
  public HashMap<String, Object> addTopMovies(Integer movie_id, Integer user_id, Integer rank, String description){
	  HashMap<String, Object> topMoviesResponse = new HashMap<String, Object>();
	  
	  if(movie_id == null|| user_id == null || rank == null || description == null) {
		topMoviesResponse.put("isSuccess", false);
  		topMoviesResponse.put("status", HttpStatus.NOT_ACCEPTABLE);
  		return topMoviesResponse; 
	  }
	  UserObject userObj;
	  try {
		  userObj = userRepository.getOne(user_id);
		  userObj.getId();
		  topMoviesResponse.put("isSuccess", true);
	  	  topMoviesResponse.put("status", HttpStatus.OK);
	  } catch(Exception e) {
		  	topMoviesResponse.put("isSuccess", false);
	  		topMoviesResponse.put("status", HttpStatus.NOT_ACCEPTABLE);
	  		return topMoviesResponse; 
	  }
	  
    try{
    TopMoviesObject obj = new TopMoviesObject(movie_id, user_id);
      obj.setRank(rank);
      obj.setDescription(description);
    topMoviesRepository.save(obj);
    topMoviesResponse.put("isSuccess", true);
    topMoviesResponse.put("status", HttpStatus.OK);
    }
    catch(Exception e){
    	    topMoviesResponse.put("isSuccess", false);
    		topMoviesResponse.put("status", HttpStatus.NOT_ACCEPTABLE);
    		
  }
  return topMoviesResponse;
}
  
  @RequestMapping("/api/movie/getTopMovies/")
  public Map<String, Object> getTopMoviesObjects(Integer user_id, Integer limit) {
    HashMap<String, Object> topMoviesResponse = new HashMap<String, Object>();
    
	  if(user_id == null || limit == null) {
		topMoviesResponse.put("isSuccess", false);
		topMoviesResponse.put("status", HttpStatus.NOT_ACCEPTABLE);
		return topMoviesResponse; 
	  }
	  
	  UserObject userObj;
	  
	  try {
		  userObj = userRepository.getOne(user_id);
		  List<TopMoviesObject> topMovies = topMoviesRepository.findByUserId(userObj.getId());
		  topMoviesResponse.put("movies", topMovies);
		  
		   topMoviesResponse.put("isSuccess", true);
		   topMoviesResponse.put("status", HttpStatus.OK);
	  } catch(Exception e) {
		  
	  }
	  
    return topMoviesResponse;
}
}
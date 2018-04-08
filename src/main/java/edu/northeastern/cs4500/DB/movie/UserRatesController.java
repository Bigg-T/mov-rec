package edu.northeastern.cs4500.DB.movie;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class UserRatesController {

    @Autowired
    UserRatesRepository userRatesRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/test/rate")
    public List<UserRatesObject> getAllUserRates() {
//      return getOnlyUserWatchedRate();
      return userRatesRepository.getAllUserRating();
    }
    @GetMapping("/api/test/avg/")
    public Integer getMovieAvgRate(int movie_id) {
      return userRatesRepository.getRatingWithMovieId(movie_id);
    }
//
    @GetMapping("/api/rate/ids/")
    public List<UserRatesObject> getUserRates(int user_id) {
      return userRatesRepository.getReccomendedMovieforUser(user_id);
    }

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
        user.getEmail();// indirectly check for user exist
        UserRatesObject obj = new UserRatesObject(movie_id, user_id,rate);
        obj.setRate(rate);
        userRatesRepository.save(obj);
        userRatesData.put("isSuccess", true);
        userRatesData.put("status", HttpStatus.OK);
        userRatesData.put("userRatingId", obj.getId());
        return userRatesData;
      } catch(Exception e) {
        userRatesData.put("isSuccess", false);
        userRatesData.put("message", "User Does Not Exist");
        userRatesData.put("status", HttpStatus.NOT_FOUND);
        return userRatesData;
      }
    }

    @RequestMapping("/api/movie/getUserRates/")
    public HashMap<String, Object> getTopMovies(Integer user_id) {
      HashMap<String, Object> userRatesData = new HashMap<String, Object>();
      List<UserRatesObject> userTopMovies = userRatesRepository.findByUserId(user_id);
      userRatesData.put("isSuccess", true);
      userRatesData.put("status", HttpStatus.OK);
      userRatesData.put("topMovies", userTopMovies);
      return userRatesData;
    }
  }
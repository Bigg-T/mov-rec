package edu.northeastern.cs4500.DB.movie;

import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


import edu.northeastern.cs4500.DB.movie.UserRatesObject;
import edu.northeastern.cs4500.DB.movie.UserRatesRepository;

@RestController
public class UserRatesController {

    @Autowired
    UserRatesRepository userRatesRepository;


    @GetMapping("/api/movie/addTopMovie/{movie_id}/{user_id}/{rate}/{description}")
    public ResponseEntity<String> addTopMovies(Integer movie_id, Integer user_id, Integer rate){
      try{
        UserRatesObject obj = new UserRatesObject(movie_id, user_id);
        obj.setRate(rate);
        userRatesRepository.save(obj);}
      catch(Exception e){
        return new ResponseEntity<String>("False", HttpStatus.NOT_ACCEPTABLE);
      }
      return new ResponseEntity<String>("True", HttpStatus.OK);
    }

    @RequestMapping("/api/movie/getTopMovies/{use_id}")
    public List<UserRatesObject> selectAllHelloObjects(Integer user_id) {
      List<UserRatesObject> userTopMovies = userRatesRepository.findByUserId(user_id);
      return userTopMovies;
    }
  }


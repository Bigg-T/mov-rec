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

/**
 * Created by amrita on 2/19/18.
 *
 */


import edu.northeastern.cs4500.DB.movie.TopMoviesObject;
import edu.northeastern.cs4500.DB.movie.TopMoviesRepository;

@RestController
public class TopMoviesController {

  @Autowired
  TopMoviesRepository topMoviesRepository;


  @GetMapping("/api/movie/addTopMovie/{movie_id}/{user_id}/{rank}/{description")
  public ResponseEntity<String> addTopMovies(Integer movie_id, Integer user_id, Integer rank, String description){
    try{
    TopMoviesObject obj = new TopMoviesObject(movie_id, user_id);
      obj.setRank(rank);
      obj.setDescription(description);
    topMoviesRepository.save(obj);}
    catch(Exception e){
      return new ResponseEntity<String>("False", HttpStatus.NOT_ACCEPTABLE);
  }
    return new ResponseEntity<String>("True", HttpStatus.OK);
  }

  @RequestMapping("/api/movie/getTopMovies/{use_id}/{limit}")
  public List<TopMoviesObject> selectAllHelloObjects(Integer user_id, Integer limit) {
    List<TopMoviesObject> userTopMovies = topMoviesRepository.findByUserId(user_id, limit);
    return topMoviesRepository.findAll();
  }

}

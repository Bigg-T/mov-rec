package edu.northeastern.cs4500.DB.movie;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import info.movito.themoviedbapi.model.MovieDb;

@CrossOrigin(origins = "http://m0vi3h4ll.s3-website.us-east-2.amazonaws.com")//{//"http://localhost:3000", 
		//"http://movi3hall.cs4500.com2.s3-website.us-east-2.amazonaws.com/"}, maxAge = 3600)
@RestController
public class MovieController {

	@Autowired
	MovieService movieService;
	
	@GetMapping("/api/movie/{id}")
	public @ResponseBody ResponseEntity<String>
	  getByName(@PathVariable String id) {
	    return new ResponseEntity<String>("GET Response : "
	      + id, HttpStatus.OK);
	}
	
	/**
	 * This method should return a JSON format of tmdbApi objects to the front end
	 * It receives a genre as a JSON object, queries the back-end for the tmdbAPI 
	 * Id's of the best rated movies, which then queries the front-end API for each of these 
	 * movies one by one. 
	 */
	@RequestMapping("/api/movie/genre/")
	public HashMap<String, Object> getMovieGenre(String genre) {
		List<MovieDb> ans = movieService.getMovieGenre(genre);
		HashMap<String, Object> ansHash = new HashMap<>();
		ansHash.put("page", 1);
		ansHash.put("total_results", 20055);
		ansHash.put("total_pages", 1003);
		ansHash.put("results", ans);
		return ansHash;
	}
	
	/**
	 * Should return a number of popular movies from 2014,15 equal to the given number 
	 * 	This method should be more random in the future. --Remove this line when that happens.
	 * @param num given number.
	 * @return
	 */
	@RequestMapping("/api/movie/popular/")
	@CrossOrigin("http://m0vi3h4ll.s3-website.us-east-2.amazonaws.com/")
	public HashMap<String, Object> getMoviePopular(Integer num) {
		//Need to sanitize input to make sure it is an integer here
		List<MovieDb> ans = movieService.getMoviePopular(num);
		HashMap<String, Object> ansHash = new HashMap<>();
		ansHash.put("page", 1);
		ansHash.put("total_results", 20055);
		ansHash.put("total_pages", 1003);
		ansHash.put("results", ans);
		return ansHash;
	}
	
	/**
	 * This movie should just return a different set of movies per user.
	 */
	@RequestMapping("/api/movie/userId/")
	public HashMap<String, Object> getMovieUser(Integer userId) {
		List<MovieDb> ans = movieService.getMovieUser(userId);
		HashMap<String, Object> ansHash = new HashMap<>();
		ansHash.put("page", 1);
		ansHash.put("total_results", 20055);
		ansHash.put("total_pages", 1003);
		ansHash.put("results", ans);
		return ansHash;
	}
}

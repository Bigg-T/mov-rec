package edu.northeastern.cs4500.DB.movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs4500.JPARepositories.MovieRatingRepository;
import edu.northeastern.cs4500.models.MovieRatingsObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
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
	public List<MovieDb> getMovieGenre(String genre) {
		List<MovieDb> ans = movieService.getMovieGenre(genre);
		return ans;
	}
	
	/**
	 * Should return a number of popular movies from 2014,15 equal to the given number 
	 * 	This method should be more random in the future. --Remove this line when that happens.
	 * @param num given number.
	 * @return
	 */
	@RequestMapping("api/movie/popular/")
	public List<MovieDb> getMoviePopular(Integer num) {
		List<MovieDb> ans = movieService.getMoviePopular(num); 
		return ans;
	}
	
	/**
	 * This movie should just return a different set of movies per user.
	 */
	@RequestMapping("/api/movie/userId/")
	public List<MovieDb> getMovieUser(Integer userId) {
		List<MovieDb> ans = movieService.getMovieUser(userId);
		return ans;
	}
}

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
	MovieRepository movieRepository;
	
	@Autowired
	GenreRepository genreRepository;
	
	@Autowired
	MovieRatingRepository movieRatingsRepository;
	
	/**
	 * I guess we can currently send through URL, but we should really be sending
	 * get requests
	 * @return
	 */
	@RequestMapping("/api/movie/create")
	public void createMovie() {
		MovieObject obj = new MovieObject("My Name is Khan", 8.3);
		movieRepository.save(obj);
		//return obj;
	}
	
	@RequestMapping("/api/movie/all_movies")
	public List<MovieObject> selectAllUserObjects() {
       // movieRepository.deleteAllInBatch();
     //   genreRepository.deleteAllInBatch();
		return movieRepository.findAll();
	}
	
	
//	@PostMapping("/api/movie/add/{name}")
//	public @ResponseBody ResponseEntity<String> post(@PathVariable String name) {
//	    return new ResponseEntity<String>("POST Response: " + name, HttpStatus.OK);
//	}
	
	//Should have the following methods:
	
	@GetMapping("/api/movie/{id}")
	public @ResponseBody ResponseEntity<String>
	  getByName(@PathVariable String id) {
	    return new ResponseEntity<String>("GET Response : "
	      + id, HttpStatus.OK);
	}
	
	//Note: Sometimes it saved multiple times, talk to TA to figure out why
	@RequestMapping("/api/movie/movie_category")
	public void movieCategoryTest() {
		MovieObject movie1 = new MovieObject("The TEST", 8.9);
		GenreObject genre1 = new GenreObject("Sci-Fi");
		movie1.getGenres().add(genre1); //adds a genre to a movie
		movieRepository.save(movie1);
	}
	
	/**
	 * This method should return a JSON format of tmdbApi objects to the front end
	 * It receives a genre as a JSON object, queries the back-end for the tmdbAPI 
	 * Id's of the best rated movies, which then queries the front-end API for each of these 
	 * movies one by one. 
	 */
	@RequestMapping("/api/movie/genre/")
	public List<MovieDb> getMovieGenre(String genre) {
		//1) Get the top rated movies in this genre.
		
		List<Integer> tmdbApiByRating = movieRatingsRepository.gettmdbIdByGenre(genre);
		TmdbMovies movies = new TmdbApi("492a79d4999e65c2324dc924891cb137").getMovies();
		List<MovieDb> tmdbApiMovies = new ArrayList<MovieDb>();
			
		//2) Query tmdbAPI
		//We'll return the top 10 best movies.
		for (int i = 0; i < 10; i++) {
			Integer tmdbId = tmdbApiByRating.get(i);
			if (tmdbId != 0) {
				MovieDb curMovie = movies.getMovie(tmdbId, "en");
				tmdbApiMovies.add(curMovie);
			}
		}
		return tmdbApiMovies;
	}
	
	/**
	 * Should return a number of popular movies from 2014,15 equal to the given number 
	 * 	This method should be more random in the future. --Remove this line when that happens.
	 * @param num given number.
	 * @return
	 */
	@RequestMapping("api/movie/popular/")
	public Integer getMoviePopular(Integer num) {
		//860 tmdbApi's in here
		List<Integer> tmdbApiByRating = movieRatingsRepository.gettmdbIdByInteger();
		
		TmdbMovies movies = new TmdbApi("492a79d4999e65c2324dc924891cb137").getMovies();
		List<MovieDb> tmdbApiMovies = new ArrayList<MovieDb>();
			
		int j = 0;
		
		//We'll return the top num best movies.
		for (int i = 0; i < num; i++) {
			if (i % 2 == 0) {
				j = i;
			} else {
				j = i * 2;
			}
			Integer tmdbId = tmdbApiByRating.get(j);
			
			if (tmdbId != 0) {
				MovieDb curMovie = movies.getMovie(tmdbId, "en");
				tmdbApiMovies.add(curMovie);
			}
		}
		return tmdbApiMovies.size();
	}
	
	/**
	 * This movie should just return a different set of movies per user.
	 */
	@RequestMapping("/api/movie/userId/")
	public List<MovieDb> getMovieUser(Integer userId) {
		String genres[]={"Comedy", "Crime", "Action", "Documentary", "Drama", 
				"Sci-fi", "Horror", "Horror", "Thriller", "Children"};
		
		Random rand = new Random();
		int genreIndex = rand.nextInt(9);
		
		//Supposed to generate a random genre of movie per user.
		String genre = genres[genreIndex];
		
		List<Integer> tmdbApiByRating = movieRatingsRepository.gettmdbIdByGenre(genre);
		TmdbMovies movies = new TmdbApi("492a79d4999e65c2324dc924891cb137").getMovies();
		List<MovieDb> tmdbApiMovies = new ArrayList<MovieDb>();
			
		//2) Query tmdbAPI
		//We'll return the top 10 best movies.
		for (int i = 0; i < 10; i++) {
			Integer tmdbId = tmdbApiByRating.get(i);
			if (tmdbId != 0) {
				MovieDb curMovie = movies.getMovie(tmdbId, "en");
				tmdbApiMovies.add(curMovie);
			}
		}
		return tmdbApiMovies;
	}
}

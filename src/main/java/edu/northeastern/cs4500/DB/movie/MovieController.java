package edu.northeastern.cs4500.DB.movie;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class MovieController {

	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	GenreRepository genreRepository;
	
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
	
}

package edu.northeastern.cs4500.DB.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class MovieController {

	@Autowired
	MovieRepository MovieRepository;
	
	/**
	 * I guess we can currently send through URL, but we should really be sending
	 * get requests
	 * @return
	 */
	@RequestMapping("/api/movie/create")
	public MovieObject createMovie() {
		MovieObject obj = new MovieObject("My Name is Khan", 8.3);
		MovieRepository.save(obj);
		return obj;
	}
	
	@RequestMapping("/api/movie/all_movies")
	public List<MovieObject> selectAllUserObjects() {
		return MovieRepository.findAll();
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
}

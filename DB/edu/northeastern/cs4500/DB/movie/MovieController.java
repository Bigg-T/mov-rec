package edu.northeastern.cs4500.DB.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public MovieObject createUser() {
		MovieObject obj = new MovieObject(1, 4.3);
		MovieRepository.save(obj);
		return obj;
	}
	
	@RequestMapping("/api/movie/all_movies")
	public List<MovieObject> selectAllUserObjects() {
		return MovieRepository.findAll();
	}
	
}

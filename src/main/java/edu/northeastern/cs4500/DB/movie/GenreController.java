package edu.northeastern.cs4500.DB.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenreController {

	@Autowired
	GenreRepository genreRepository;
	
	@RequestMapping("/api/genre/create")
	public GenreObject createMovie() {
		GenreObject obj = new GenreObject("Action");
		genreRepository.save(obj);
		return obj;
	}
	
	@RequestMapping("/api/genre/all_genres")
	public List<GenreObject> selectAllUserObjects() {
		return genreRepository.findAll();
	}
}

package edu.northeastern.cs4500.controllers.hello;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;

@RestController
public class HelloController {

	@Autowired
	HelloRepository helloRepository;

	@RequestMapping("/api/hello/insert")
	public HelloObject insertHelloObject() {
		HelloObject obj = new HelloObject("Hello Thien");
		helloRepository.save(obj);
		return obj;
	}

	@RequestMapping("/api/hello/insert/{msg}")
	public HelloObject insertMessage(@PathVariable("msg") String message) {
		HelloObject obj = new HelloObject(message);
		helloRepository.save(obj);
		return  obj;
	}

	@RequestMapping("/api/hello/select/all")
	public List<HelloObject> selectAllHelloObjects() {
		return helloRepository.findAll();
	}

	@RequestMapping("/api/hello/string")
	public String sayHello( ) {
		return "Hello Thien!";
	}
	
	@RequestMapping("/api/hello/object")
	public MovieDb sayHelloObject() {
		TmdbMovies movies = new TmdbApi("492a79d4999e65c2324dc924891cb137").getMovies();
		MovieDb obj = movies.getMovie(5353, "en");
		//HelloObject obj = new HelloObject("Hello Thien!");
		return obj;
	}
}

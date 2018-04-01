package edu.northeastern.cs4500;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.northeastern.cs4500.models.MovieRatingsObject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Cs4500Spring2018NguyenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieRestControllerTest {

	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
    
	@Test
	public void testGetSingleMovie() throws Exception {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		Integer goodTmdbId = 10862;
		
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/movie/getMovie/?tmdbId=" + goodTmdbId),
				HttpMethod.GET, entity, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> responseList = mapper.readValue(response.getBody(), new TypeReference<HashMap<String,Object>>() {});
		@SuppressWarnings("unchecked")
		LinkedHashMap<String, Object> movieDb = (LinkedHashMap<String, Object>) responseList.get("results");
		Assert.assertEquals(true, movieDb.get("id").equals(10862));
	}
	
	@Test
	public void testGetSingleMovieTmdbIdDoesntExist() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		Integer badTmdbId = -1;
		ResponseEntity<HashMap> response = restTemplate.exchange(
				createURLWithPort("/api/movie/getMovie/?tmdbId=" + badTmdbId),
				HttpMethod.GET, entity, HashMap.class);
		HashMap<String, Object> responseHash = response.getBody();
		MovieDb givenMovie = (MovieDb) responseHash.get("results");
		Assert.assertEquals(true, givenMovie == null);
	}
	
	
	@Test
	public void testGetPopularMovie() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		int numMovies = 4;
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/movie/popular/?num=" + numMovies),
				HttpMethod.GET, entity, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> responseList = mapper.readValue(response.getBody(), new TypeReference<HashMap<String,Object>>() {});
		@SuppressWarnings("unchecked")
		List<MovieDb> movieDbList = (List<MovieDb>) responseList.get("results");
			    
		
		Assert.assertEquals(movieDbList.size(), numMovies);
	}
	
	
	
	@Test
	public void testGetPopularMovieMany() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		int numMovies = 87;
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/movie/popular/?num=" + numMovies),
				HttpMethod.GET, entity, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> responseList = mapper.readValue(response.getBody(), new TypeReference<HashMap<String,Object>>() {});
		@SuppressWarnings("unchecked")
		List<MovieDb> movieDbList = (List<MovieDb>) responseList.get("results");
		
		Assert.assertEquals(movieDbList.size(), 50);
	}
	
	@Test
	public void testGetGenre() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		String genre = "Thriller";
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/movie/genre/?genre=" + genre),
				HttpMethod.GET, entity, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> responseList = mapper.readValue(response.getBody(), new TypeReference<HashMap<String,Object>>() {});
		@SuppressWarnings("unchecked")
		List<MovieDb> movieDbList = (List<MovieDb>) responseList.get("results");
		
		Assert.assertEquals(movieDbList.size(), 10);
	}
	
	@Test
	public void testGetMovieUser() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		int userId = 12;
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/movie/userId/?userId=" + userId),
				HttpMethod.GET, entity, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> responseList = mapper.readValue(response.getBody(), new TypeReference<HashMap<String,Object>>() {});
		@SuppressWarnings("unchecked")
		List<MovieDb> movieDbList = (List<MovieDb>) responseList.get("results");
		
		Assert.assertEquals(movieDbList.size(), 10);
	}
	
	@Test
	public void testMovieRatingsObj() throws Exception {
		MovieRatingsObject mobject = new MovieRatingsObject();
		mobject.setGenre("Comedy");
		Assert.assertEquals(true, mobject.getGenres().equals("Comedy"));
		mobject.setmovieId(22222);
		Assert.assertEquals(true, mobject.getmovieId() == 22222);
		mobject.setRating(3.5);
		Assert.assertEquals(true, mobject.getRating() == 3.5);
		mobject.setTitle("exampleTitle");
		Assert.assertEquals(true, mobject.getTitle().equals("exampleTitle"));
		mobject.setTmdbId(4124);
		Assert.assertEquals(true, mobject.getTmdbId() == 4124);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
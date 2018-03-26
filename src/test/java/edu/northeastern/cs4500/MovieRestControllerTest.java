package edu.northeastern.cs4500;

import java.util.HashMap;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Cs4500Spring2018NguyenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieRestControllerTest {

	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
    
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
		
//		for (int i = 0; i < movieDbList.size(); i++) {
//			List<Genre> genres = movieDbList.get(i).getGenres();
//			Assert.assertEquals(true, genreContained(genres, genre));
//		}
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
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
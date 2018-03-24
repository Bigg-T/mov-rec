package edu.northeastern.cs4500;

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

import info.movito.themoviedbapi.model.MovieDb;

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

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/movie/popular/?num=9"),
				HttpMethod.GET, entity, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<MovieDb> movieDbList =
			    mapper.readValue(response.getBody(), new TypeReference<List<MovieDb>>() {});
		
		for (int i = 0; i < movieDbList.size(); i++) {
			MovieDb curMovie = movieDbList.get(i);
			System.out.println(curMovie.getPopularity());
			System.out.println(movieDbList.get(i).getTitle());
		}
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
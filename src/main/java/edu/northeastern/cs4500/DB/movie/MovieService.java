package edu.northeastern.cs4500.DB.movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import org.springframework.stereotype.Component;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;

import org.springframework.beans.factory.annotation.Autowired;

import edu.northeastern.cs4500.JPARepositories.MovieRatingRepository;

@Component
public class MovieService {

	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	MovieRatingRepository movieRatingsRepository;
	
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
	 * This method returns a list of distinct random
	 * integers between 0 and whatever the maximum number is (inclusive).
	 * @param max the largest number in the set.
	 */
	private List<Integer> getDistinctRandomNums(Integer max) {
		List<Integer> ans = new ArrayList<Integer>();
		for(int i = 0; i < max; i++) {
			ans.add(i);
		}
		Collections.shuffle(ans);
		return ans;
	}
	
	public List<MovieDb> getMoviePopular(Integer num) {
		//860 tmdbApi's in here
		List<Integer> tmdbApiByRating = movieRatingsRepository.gettmdbIdByInteger();
		
		TmdbMovies movies = new TmdbApi("492a79d4999e65c2324dc924891cb137").getMovies();
		List<MovieDb> tmdbApiMovies = new ArrayList<MovieDb>();
		
		List<Integer> chosenIds = getDistinctRandomNums(num);
		
		//We'll return the top num best movies.
		for (int i = 0; i < num; i++) {
			
			Integer tmdbId = tmdbApiByRating.get(chosenIds.get(i));
			
			if (tmdbId != 0) {
				MovieDb curMovie = movies.getMovie(tmdbId, "en");
				tmdbApiMovies.add(curMovie);
			}
		}
		return tmdbApiMovies;
	}
	
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

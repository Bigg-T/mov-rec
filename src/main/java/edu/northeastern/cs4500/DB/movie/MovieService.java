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
		List<MovieDb> tmdbApiMovies = new ArrayList<MovieDb>();
			
		//2) Query tmdbAPI
		//We'll return the top 10 best movies.
		for (int i = 0; i < 10; i++) {
			Integer tmdbId = tmdbApiByRating.get(i);
			if (tmdbId != 0) {
				addMovieToList(tmdbId, tmdbApiMovies);
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
	
	/**
	 * This method returns up to 50 random and popular movies
	 * based off of movielens database data. It queries the
	 * tmdbAPI with this data and returns those movies. 
	 * @param num number of movies to return.
	 */
	public List<MovieDb> getMoviePopular(Integer num) {
		if (num > 50) { num = 50; }
		
		//860 tmdbApi's in here
		List<Integer> tmdbApiByRating = movieRatingsRepository.gettmdbIdByInteger();
		
		List<MovieDb> tmdbApiMovies = new ArrayList<MovieDb>();
		
		int numOfRandoms = num * 5;
		List<Integer> chosenIds = getDistinctRandomNums(numOfRandoms);
		
		//We'll return the top num best movies.
		for (int i = 0; i < num; i++) {
			Integer tmdbId = tmdbApiByRating.get(chosenIds.get(i));
			if (tmdbId != 0) {
				addMovieToList(tmdbId, tmdbApiMovies);
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
		List<MovieDb> tmdbApiMovies = new ArrayList<MovieDb>();
			
		//2) Query tmdbAPI
		//We'll return the top 10 best movies.
		for (int i = 0; i < 10; i++) {
			Integer tmdbId = tmdbApiByRating.get(i);
			if (tmdbId != 0) {
				addMovieToList(tmdbId, tmdbApiMovies);
			}
		}
		return tmdbApiMovies;
	}
	
	/**
	 * This method queries the tmdbApi for a film and adds it to the given
	 * list if it is found.
	 * @param tmdbId The ID to query the API for.
	 * @param movies The list of movies to add the supposed movie to.
	 */
	private void addMovieToList(int tmdbId, List<MovieDb> moviesList) {
		TmdbMovies movies = new TmdbApi("492a79d4999e65c2324dc924891cb137").getMovies();
		try {
			MovieDb curMovie = movies.getMovie(tmdbId, "en");
			moviesList.add(curMovie);
		} catch (Exception e) {
			//This is an error:
			//We were trying to search for a film and we could not find it
			//In the tmdbAPI.
			//We should log which film this was.
		}
	}
}

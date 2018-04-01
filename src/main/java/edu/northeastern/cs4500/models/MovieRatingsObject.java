package edu.northeastern.cs4500.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name="movie_ratings")
public class MovieRatingsObject {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int movie_id;
	
	@NotNull
	private String title;
	
	@NotNull
	private String genres;
	
	private Double rating;
	
	private int tmdb_id;
	//MOVIE HALL RATING
	private double vote_average;
	private int vote_count;
	//MOVIE HALL RATING
	
	//Empty constructor
	public MovieRatingsObject() {
		this.vote_count = 0;
		this.vote_average = 0;
		
	}
	
	public int getmovieId() {
		return this.movie_id;
	}
	
	public String getTitle() {
		return this.title;
	}
	public String getGenres() {
		return this.genres;
	}
	public double getRating() {
		return this.rating;
	}
	public int getTmdbId() {
		return this.tmdb_id;
	}
	
	public void setmovieId(int m) {
		this.movie_id = m;
	}
	
	public void setTitle(String t) {
		this.title = t; 
	}

	public void setGenre(String g) {
		this.genres = g;
	}
	
	public void setRating(Double r) {
		this.rating = r;
	}
	
	public void setTmdbId(Integer t) {
		this.tmdb_id = t;
	}
	
	/**
	 * recalculates the average vote average for this movie
	 * @param vote 
	 */
	public void recalculateVoteAverage(double vote) {
		this.vote_count++;
		this.vote_average = (this.vote_average + vote) / this.vote_count;
	}
}

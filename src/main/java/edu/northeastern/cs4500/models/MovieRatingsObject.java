package edu.northeastern.cs4500.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name="MovieRatings")
public class MovieRatingsObject {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int movieId;
	
	@NotNull
	private String title;
	
	@NotNull
	private String genres;
	
	private Double rating;
	
	private int tmdbId;
	
	//Empty constructor
	public MovieRatingsObject() {
		
	}
	
	public int getmovieId() {
		return this.movieId;
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
		return this.tmdbId;
	}
	
	public void setmovieId(int m) {
		this.movieId = m;
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
		this.tmdbId = t;
	}
}

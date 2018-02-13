package edu.northeastern.cs4500.DB.movie;

import javax.persistence.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Data model for Movie table
 * Can add more columns if we want to store more data that the API gives us
 */
@Entity(name="Movie")
public class MovieObject {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(unique=true)
	private int movie_id;
	
	private double rating;
	
	public MovieObject() {
		
	}
	
	public MovieObject(int movie_id, double rating) {
		this.movie_id = movie_id;
		this.rating = rating;
	}
	

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
		
}

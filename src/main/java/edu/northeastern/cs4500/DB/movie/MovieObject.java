package edu.northeastern.cs4500.DB.movie;

import javax.persistence.Entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

//import javax.persistence.JoinColumn;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

/**
 * Data model for Movie table
 */
@Entity(name="Movie")
public class MovieObject {
	
	//@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
    private int movieId; //movie id saved from API we are using
	
	@NotNull
	private String title;
	private String overview; //description
	private int populatiry;
	private String poster_path;
	private int release_date;
	private String video_link; // Not sure if this is necessary, probably would store the youtube clip link
	private double vote_average;
	private int vote_count;
	private String movie_rating; //This would preferably be an Enum (G, PG-13, R..) Change later if you have time
	private Set<GenreObject> genres; //MANY TO MANY relationship with Genres Class
	
	public MovieObject() {
		//Set<GenreObject> test = new HashSet<>();
		this.genres = new HashSet<>();
//		private Set<GenreObject> roles = new HashSet()<>;
//		this.genres = new Set<>;;
	}
	
	public MovieObject(String title, double vote_average) {
		this.vote_count = 1;
		this.title = title;
		this.vote_average = vote_average;
		this.genres = new HashSet<>();
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	public double getRating() {
		return this.vote_average;
	}

	public void setRating(double vote_average) {
		this.vote_average = vote_average;
	}

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public int getPopulatiry() {
		return populatiry;
	}

	public void setPopulatiry(int populatiry) {
		this.populatiry = populatiry;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public int getRelease_date() {
		return release_date;
	}

	public void setRelease_date(int release_date) {
		this.release_date = release_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideo_link() {
		return video_link;
	}

	public void setVideo_link(String video_link) {
		this.video_link = video_link;
	}

	public double getVote_average() {
		return vote_average;
	}

	public void setVote_average(double vote_average) {
		this.vote_average = vote_average;
	}

	public int getVote_count() {
		return vote_count;
	}

	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}

	public String getMovie_rating() {
		return movie_rating;
	}

	public void setMovie_rating(String movie_rating) {
		this.movie_rating = movie_rating;
	}
	
	/**
	 * recalculates the average vote average for this movie
	 * @param vote 
	 */
	public void recalculateVoteAverage(double vote) {
		this.vote_count++;
		this.vote_average = (this.vote_average + vote) / this.vote_count;
	}

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movieGenre", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    public Set<GenreObject> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreObject> genres) {
        this.genres = genres;
    }

	public int getMovie_id() {
		return movieId;
	}

	public void setMovie_id(int movie_id) {
		this.movieId = movie_id;
	}
		
}

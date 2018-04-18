package edu.northeastern.cs4500.DB.movie;


import java.util.Set;

/**
 * Returns a list of the user's top movies
 */


import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name="TopMovies")
public class TopMoviesObject {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int id;

  @NotNull
  private int movie_id;
  @NotNull
  private int user_id;
  private String description;
  private int rank;

  TopMoviesObject() {

  }

  public TopMoviesObject(int movie_id, int user_id) {
    this.user_id = user_id;
    this.movie_id = movie_id;
  }


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getMovie_id() {
    return movie_id;
  }

  public void setMovie_id(int movie_id) {
    this.movie_id = movie_id;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
	 if(user_id <0) {
		 System.out.println("false");
	 }
	 else {
    this.user_id = user_id;
	 }
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public int getRank() {
    return rank;
  }

  public int setRank(int rank) {
    return this.rank = rank;
  }

}
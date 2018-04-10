/**
 * Returns user rates table
 */

package edu.northeastern.cs4500.DB.movie;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name="UserRates")
public class UserRatesObject {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int id;

  @NotNull
  private int movie_id;
  
  @NotNull
  private int user_id;
  
  @NotNull
  private int rate;

  public UserRatesObject() {

  }

  public UserRatesObject(int movie_id, int user_id) {
    this.user_id = user_id;
    this.movie_id = movie_id;
  }


  public UserRatesObject(Integer movie_id2, Integer user_id2, Integer rate2) {
	  this.user_id = user_id2;
	  this.movie_id = movie_id2;
	  this.rate = rate2;
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
    this.user_id = user_id;
  }

  public int getRate() {
    return rate;
  }

  public int setRate(int rate) {
    return this.rate = rate;
  }
	
}
/**
 * Returns user rates table
 */

package edu.northeastern.cs4500.DB.movie;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity(name="user_rates")
public class UserRatesObject {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int id;

  @NotNull
  private int movie_id;
  
  @NotNull
  private int user_id;
  
  @NotNull
  private double rate;

  @NotNull //true mean already watched
  private boolean is_watched;

  public UserRatesObject() {

  }

  public UserRatesObject(int movie_id, int user_id, double rate) {
    this.user_id = user_id;
    this.movie_id = movie_id;
    this.rate = rate;
    this.is_watched =true;
  }

  public UserRatesObject(int movie_id, int user_id, double rate, boolean is_watched) {
    this.user_id = user_id;
    this.movie_id = movie_id;
    this.rate = rate;
    this.is_watched = is_watched;
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

  public double getRate() {
    return rate;
  }

  public double setRate(double rate) {
    this.rate = rate;
    return this.rate;
  }

  public void setIs_watched(boolean is_watched) {
    this.is_watched = is_watched;
  }
  public boolean getIs_watched() {
    return this.is_watched;
  }
	
}
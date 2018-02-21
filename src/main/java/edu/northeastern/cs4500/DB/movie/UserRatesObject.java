/**
 * Returns user rates table
 */

package edu.northeastern.cs4500.DB.movie;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name="UserRates")
public class UserRatesObject {

  @Id
  @OneToMany
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int id;

  @NotNull
  private int movie_id;
  @NotNull
  private int user_id;
  private int rate;

  UserRatesObject() {

  }

  UserRatesObject(int movie_id, int user_id) {
    this.user_id = user_id;
    this.movie_id = movie_id;
  }


  @Id
  @OneToMany
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
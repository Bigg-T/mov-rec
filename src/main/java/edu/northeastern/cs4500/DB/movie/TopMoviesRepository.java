package edu.northeastern.cs4500.DB.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by amrita on 2/19/18.
 */
public interface TopMoviesRepository extends JpaRepository<TopMoviesObject, Integer> {

  @Query("SELECT * FROM TopMovies t WHERE t.user_id = :id")
  List<TopMoviesObject> findByUserId(@Param("id") int id, @Param("lim") int lim);

}

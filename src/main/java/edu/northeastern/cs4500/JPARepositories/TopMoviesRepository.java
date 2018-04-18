package edu.northeastern.cs4500.JPARepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs4500.DB.movie.TopMoviesObject;

import java.util.List;

/**
 * Created by amrita on 2/19/18.
 */
public interface TopMoviesRepository extends JpaRepository<TopMoviesObject, Integer> {

  @Query(value="SELECT * FROM top_movies t WHERE t.user_id = ?1", nativeQuery = true)
  List<TopMoviesObject> findByUserId(int user_id);

}

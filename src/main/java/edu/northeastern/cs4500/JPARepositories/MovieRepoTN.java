package edu.northeastern.cs4500.JPARepositories;

import edu.northeastern.cs4500.models.MovieRatingsObject;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by t on 4/8/18.
 */
@Repository
public interface MovieRepoTN extends JpaRepository<MovieRatingsObject, Integer> {
  @Query(value = "SELECT tmdb_id FROM movie_ratings WHERE"
          // Later on we can distinguish between what year we want the movies from specifically,
          // but for now, we'll just return the highest rated movies of all time.
          + " title LIKE \'%(2015)%\' OR title LIKE \'%(2014)%\'"
          + " AND tmdb_id IS NOT NULL AND tmdb_id != 0 ORDER BY tmdb_id")
  public List<Integer> getAllMovies();

}

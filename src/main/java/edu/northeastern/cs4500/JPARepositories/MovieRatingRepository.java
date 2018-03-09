package edu.northeastern.cs4500.JPARepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs4500.models.MovieRatingsObject;

@Repository
public interface MovieRatingRepository extends JpaRepository<MovieRatingsObject, Integer>{
	
	@Query
	(value = "SELECT tmdbId FROM MovieRatings WHERE genres LIKE CONCAT(\'%\'" + ", ?1, " + "\'%\')"
			// Later on we can distinguish between what year we want the movies from specifically,
			// but for now, we'll just return the highest rated movies of all time.
			//+ " AND title LIKE \'%(2010)%\'" 
			+ " AND tmdbId IS NOT NULL ORDER BY rating")
	public List<Integer> gettmdbIdByGenre(String genre);
	
	
	/**
	 * Should return a list of the most popular movies from 2016 that is of size numMovies.
	 * @param numMovies This is a string so that it's easier to integrate into query.
	 */
	@Query
	(value = "SELECT tmdbId FROM MovieRatings WHERE"
			// Later on we can distinguish between what year we want the movies from specifically,
			// but for now, we'll just return the highest rated movies of all time.
			+ " title LIKE \'%(2015)%\' OR title LIKE \'%(2014)%\'" 
			+ " AND tmdbId IS NOT NULL ORDER BY rating")
	public List<Integer> gettmdbIdByInteger();
}
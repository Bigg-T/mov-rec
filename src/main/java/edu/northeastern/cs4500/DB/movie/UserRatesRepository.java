package edu.northeastern.cs4500.DB.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * UserRatesRepository for UserRatesObject
 */
public interface UserRatesRepository extends JpaRepository<UserRatesObject, Integer> {
//  @Query("SELECT * FROM UserRates t WHERE t.user_id = :id")
//  List<UserRatesObject> findByUserId(@Param("id") int id);
	
	@Query(value = "SELECT t.movie_id, t.rate FROM UserRates t WHERE t.user_id = ?1")
	public List<Integer> findByUserId(Integer user_id);
	
}

package edu.northeastern.cs4500.JPARepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs4500.DB.movie.UserRatesObject;
import edu.northeastern.cs4500.DB.user.UserObject;

import java.util.List;

/**
 * UserRatesRepository for UserRatesObject
 */
@Repository
public interface UserRatesRepository extends JpaRepository<UserRatesObject, Integer> {
//  @Query("SELECT * FROM UserRates t WHERE t.user_id = :id")
//  List<UserRatesObject> findByUserId(@Param("id") int id);
	
	@Query(value = "SELECT t.movie_id, t.rate FROM UserRates t WHERE t.user_id = ?1")
	public List<UserRatesObject> findByUserId(Integer user_id);
	
	//@Query(value = "INSERT INTO user_rates (movie_id, rate, user_id) VALUES ( ?2 , ?3 , ?1 )")
	//public List<UserRatesObject> insertIntoUserRates(Integer user_id, Integer movie_id, Integer rate);
	
	@Async
	@Query("SELECT u FROM user u WHERE u.id = ?1")
	public List<UserObject> getUserRatesData(Integer movie_id, Integer user_id, Integer rate);
}

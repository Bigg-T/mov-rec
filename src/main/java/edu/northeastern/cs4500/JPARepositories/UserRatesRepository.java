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
	
	@Query(value = "SELECT t.movie_id, t.rate FROM UserRates t WHERE t.user_id = ?1")
	public List<UserRatesObject> findByUserId(Integer user_id);
	
	@Async
	@Query("SELECT u FROM user u WHERE u.id = ?1")
	public List<UserObject> getUserRatesData(Integer movie_id, Integer user_id, Integer rate);
}

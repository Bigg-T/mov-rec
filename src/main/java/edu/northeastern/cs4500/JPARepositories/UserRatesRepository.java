package edu.northeastern.cs4500.JPARepositories;

import javax.jws.soap.SOAPBinding.Use;
import org.apache.catalina.User;
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
	
	@Query(value = "SELECT t FROM user_rates t WHERE t.user_id = ?1")
	public List<UserRatesObject> findByUserId(Integer user_id);

	@Async //for developer
	@Query(value = "SELECT t FROM user_rates t")
	public List<UserRatesObject> getAllUserRating();

	@Async //for developer getting all rating
	@Query(value = "SELECT AVG(t.rate) from user_rates t WHERE t.movie_id=?1")
	public Double getRatingWithMovieId(Integer movie_id);

	@Async // for developer, all users ratings what they watched
	@Query(value = "SELECT u from user_rates u where u.is_watched = 1")
	public List<UserRatesObject> getOnlyUserWatchedRate();

	@Async //movies user have not watched with highest rating to low
	@Query(value = "SELECT t from user_rates t WHERE t.is_watched = 0 AND t.user_id = ?1 ORDER BY t.rate")
	public List<UserRatesObject> getReccomendedMovieforUser(Integer user_id);

	@Async
	@Query("SELECT u FROM user u WHERE u.id = ?1")
	public List<UserObject> getUserRatesData(Integer movie_id, Integer user_id, Integer rate);
}

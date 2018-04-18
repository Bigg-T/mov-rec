package edu.northeastern.cs4500.JPARepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs4500.DB.movie.UserRatesObject;
import edu.northeastern.cs4500.DB.user.UserObject;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

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
	@Query(value = "SELECT t from user_rates t WHERE"
			+ " t.is_watched = FALSE AND t.user_id = ?1 ORDER BY t.rate DESC")
	public List<UserRatesObject> getReccomendedMovieforUser(Integer user_id);

	@Async //dev delete
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM user_rates t WHERE t.is_watched = false")
	public void deleteAllRec();

	@Async //get a list of movies with certain user id and movie id
	@Query(value = "SELECT t from user_rates t WHERE "
			+ "t.user_id = ?1 AND t.movie_id = ?2 AND t.is_watched = ?3")
	public List<UserRatesObject> getRateByUserIdMoiveId(Integer user_id, Integer movie_id, Boolean is_watched);

	@Async //dev delete
	@Transactional
	@Modifying
	@Query(value = "UPDATE user_rates t SET t.is_watched = true, t.rate = 0"
			+ " WHERE t.user_id=?1 AND t.movie_id = ?2")
	public void dismissRecMovie(Integer user_id, Integer movie_id);
}

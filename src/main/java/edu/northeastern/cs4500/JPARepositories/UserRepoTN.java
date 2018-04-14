package edu.northeastern.cs4500.JPARepositories;

import edu.northeastern.cs4500.DB.user.UserObject;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by t on 4/8/18.
 */
@Repository
public interface UserRepoTN extends JpaRepository<UserObject, Integer> {
  @Async
  @Query("SELECT u.id FROM user u ORDER BY u.id")
  public List<Integer> getAllIDUser();

  @Async
  @Modifying
  @Transactional
  @Query(value = "UPDATE user t SET t.logged = false WHERE t.id=?1")
  public void logoutUser(Integer id);

//  @Async
//  @Modifying
//  @Transactional
//  @Query(value = "DELETE from user u WHERE u.id = ?1")
//  public void deleteUser(Integer id);


}

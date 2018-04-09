package edu.northeastern.cs4500.JPARepositories;

import edu.northeastern.cs4500.DB.user.UserObject;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

/**
 * Created by t on 4/8/18.
 */
@Repository
public interface UserRepoTN extends JpaRepository<UserObject, Integer> {
  @Async
  @Query("SELECT u.id FROM user u ORDER BY u.id")
  public List<Integer> getAllIDUser();
}

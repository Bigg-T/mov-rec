package edu.northeastern.cs4500.JPARepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs4500.DB.user.UserObject;

@Repository
public interface UserRepository extends JpaRepository<UserObject, Integer>{

	@Async
	@Query("SELECT u FROM user u WHERE u.id = ?1")
	public List<UserObject> getUserProfileData(Integer userId);

	@Async
	@Query("SELECT u FROM user u WHERE u.username = ?1")
	public List<UserObject> getUserByUsername(String username);
}

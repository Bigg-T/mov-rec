package edu.northeastern.cs4500.DB.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserObject, Integer>{

	@Async
	@Query("SELECT first_name, last_name, prof_pic, about_me FROM User WHERE id = :id")
	public String getUserProfileData(@Param("id") Integer userId);

}

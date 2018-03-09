package edu.northeastern.cs4500.DB.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserObject, Integer>{

}

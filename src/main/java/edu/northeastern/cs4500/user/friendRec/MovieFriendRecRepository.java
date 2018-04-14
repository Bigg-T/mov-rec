package edu.northeastern.cs4500.user.friendRec;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieFriendRecRepository extends JpaRepository<MovieFriendRecObject, Integer>{

}

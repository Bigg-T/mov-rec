//package edu.northeastern.cs4500.JPARepositories;
//
//import edu.northeastern.cs4500.DB.user.UserFriendObject;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * Created by t on 4/11/18.
// */
//
//@Repository
//public interface UserFriendRepoTN extends JpaRepository<UserFriendObject, Integer> {
//
//  @Async
//  @Modifying
//  @Transactional
//  @Query(value = "DELETE FROM user_friends uf WHERE uf.user_id = ?1")
//  public void deleteFriends(Integer user_id);
//}

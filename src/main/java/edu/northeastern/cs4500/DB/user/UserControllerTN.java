package edu.northeastern.cs4500.DB.user;

import edu.northeastern.cs4500.JPARepositories.UserRepoTN;
import edu.northeastern.cs4500.JPARepositories.UserRepository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by t on 4/11/18.
 */
@CrossOrigin(origins = {"http://m0vi3h4ll.s3-website.us-east-2.amazonaws.com",
    "http://localhost:3000"})
@Controller
public class UserControllerTN {

  @Autowired
  UserRepoTN userRepoDelete;
  @Autowired
  UserRepository userRepository;
  @Autowired
  UserService userService;

//  @Autowired
//  UserFriendRepoTN userFriendRepoTN;

//  @GetMapping("/api/delete/user")
//  @ResponseBody
//  public Map<String, Object> deleteUser(Integer user_id) {
//    Map<String, Object> res = new HashMap<>();
//
//    UserObject user = userRepository.getOne(user_id);
//    Collection<UserObject> userFriends = user.getFriends();
////    for (int i = userFriends.size(); i > 0; i--) {
////      user
////    }
//    userFriends.forEach(friend -> userService.removeFriend(user_id, friend.getId()));
//    userRepoDelete.deleteUser(user_id);
//    res.put("isSuccess", true);
//    res.put("status",HttpStatus.OK);
//    return res;
//  }

  @GetMapping("/api/user/admin/")
  @ResponseBody
  public Map<String,Object> isAdminUser(Integer id) {
    Map<String, Object> temp = new HashMap<>();
    temp.put("isAdmin",userRepoDelete.isUserAnAdmin(id));
    temp.put("status",HttpStatus.OK);
    temp.put("isSuccess",true);
    return temp;
  }
}

package edu.northeastern.cs4500;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import edu.northeastern.cs4500.DB.user.UserProfile;
import edu.northeastern.cs4500.DB.user.UserService;

import java.util.Arrays;
 
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UserProfile.class, Cs4500Spring2018NguyenApplication.class})
@WebAppConfiguration
public class UserProfileTests {

	private MockMvc mockMvc;
	 
    @Autowired
    private UserService userService;
 

    /**
     * Test that the User Profile pages gets all the data it needs from the REST API for the specific user
     * @throws Exception
     */
    @Test
    public void testGetUserProfileData() throws Exception {

    }
    
    /**
     * Tests that a User can add another user
     * @throws Exception
     */
    @Test
    public void testAddNewFriend() throws Exception {

    }
    
    /**
     * Tests that a User cannot add a friend that they already had
     * @throws Exception
     */
    @Test
    public void testAddFriendAgain() throws Exception {

    }
    
    /**
     * Tests that a User can remove a friend
     * @throws Exception
     */
    @Test
    public void testRemoveFriend() throws Exception {

    }
    
    /**
     * Tests that a User can't remove a friend that he's not friends with
     * @throws Exception
     */
    @Test
    public void testRemoveWrongFriend() throws Exception {

    }
    
    /**
     * Tests that a User can get all their friends
     * @throws Exception
     */
    @Test
    public void testViewAllFriends() throws Exception {

    }
}
	


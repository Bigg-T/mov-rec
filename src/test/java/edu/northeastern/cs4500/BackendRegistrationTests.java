package edu.northeastern.cs4500;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.northeastern.cs4500.DB.user.UserController;
import edu.northeastern.cs4500.DB.user.UserObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendRegistrationTests {
	
	@Test
	public void testRegister() {
		UserController u = new UserController();
		List<UserObject> allUsers = u.selectAllUserObjects();
		assertEquals(allUsers.size(), 0);
		u.createUser();
		assertEquals(allUsers.size(), 1);
		u.addUser("Sajid", "Raihan", "raihan.s@husky.neu.edu", "badPass", "raihans");
		assertEquals(allUsers.size(), 2);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testUserNameExists() {
		UserController u = new UserController();
		u.addUser("Sajid", "Raihan", "raihan.s@husky.neu.edu", "badPass", "raihans");
		u.addUser("Sajid", "Raihan", "raihans8379@gmail.com", "badPAss", "raihans");
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testEmailExists() {
		UserController u = new UserController();
		u.addUser("Sajid", "Raihan", "raihan.s@husky.neu.edu", "badPass", "raihans");
		u.addUser("Sajid", "Raihan", "raihan.s@husky.neu.edu", "badPAss", "raihan.s");
	}
	
	@Test
	public void testGoodLogin() {
		UserController u = new UserController();
		List<UserObject> allUsers = u.selectAllUserObjects();
		u.addUser("Sajid", "Raihan", "raihan.s@husky.neu.edu", "badPass", "raihans");
		assertEquals(allUsers.size(), 1);
		boolean ans = u.validateLogin("raihans", "badPass");
		assertTrue(ans);
	}
	
	@Test
	public void testBadLogin() {
		UserController u = new UserController();
		List<UserObject> allUsers = u.selectAllUserObjects();
		u.addUser("Sajid", "Raihan", "raihan.s@husky.neu.edu", "badPass", "raihans");
		assertEquals(allUsers.size(), 1);
		boolean ans = u.validateLogin("raihans", "wrongPass");
		assertTrue(ans);
	}
	
}
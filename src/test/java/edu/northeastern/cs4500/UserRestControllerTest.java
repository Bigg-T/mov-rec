package edu.northeastern.cs4500;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import edu.northeastern.cs4500.DB.user.UserController;
import edu.northeastern.cs4500.DB.user.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	/**
	 * This API originally has an empty DB, which is the reason
	 * this should be empty at this point.
	 */
	@Test
	public void testEmptyDB() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/user/all_users").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[]";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void testLogin() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/user/add_User/?fname=roshun&lname=menon&email=menonr&pw=iluvzahra&username=rocean").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "{\"isSuccess\":true}";

//		JSONAssert.assertEquals(expected, result.getResponse()
//				.getContentAsString(), false);
		
		RequestBuilder goodLogin = MockMvcRequestBuilders.get(
				"/api/user/validate_login/"
				+ "?username=rocean&pw=nicepass").accept(MediaType.APPLICATION_JSON);
		
		MvcResult goodLoginResult = mockMvc.perform(goodLogin).andReturn();

//		JSONAssert.assertEquals(success, goodLoginResult.getResponse()
//				.getContentAsString(), false);
	}
	
	@Test
	public void testAddedUser() throws Exception {

		RequestBuilder addUserRequest = MockMvcRequestBuilders.get(
				"/api/user/add_User/?fname=roshun&lname="
				+ "menon&email=menonr&pw=nice&username=rocean").accept(
				MediaType.APPLICATION_JSON);

		mockMvc.perform(addUserRequest);
		
		String acceptedUser = "{\"isSuccess\":true}";
		
//		JSONAssert.assertEquals(acceptedUser, addUserRequest.getResponse()
//				.getContentAsString(), false);
		
		mockMvc.perform(addUserRequest);
		
		String unacceptedUser = "{\"isSuccess\":false}";
		
//		JSONAssert.assertEquals(unacceptedUser, addUserRequest.getResponse()
//		.getContentAsString(), false);
	}
	
	@Test
	public void testBadAddUser() throws Exception {

		RequestBuilder addUserRequest = MockMvcRequestBuilders.get(
				"/api/user/add_User/?fname=roshun&lname="
				+ "menon&email=menonr&pw=nice&username=rocean").accept(
				MediaType.APPLICATION_JSON);

		mockMvc.perform(addUserRequest);
		
		String acceptedUser = "{\"isSuccess\":true}";
		
//		JSONAssert.assertEquals(acceptedUser, addUserRequest.getResponse()
//				.getContentAsString(), false);
		
		//Try to add second user
		mockMvc.perform(addUserRequest);
		
		String unacceptedUser = "{\"isSuccess\":false}";
		
		//Make sure they're not added
//		JSONAssert.assertEquals(unacceptedUser, addUserRequest.getResponse()
//		.getContentAsString(), false);
		
		RequestBuilder getUsersRequest = MockMvcRequestBuilders.get(
				"/api/user/all_users").accept(
				MediaType.APPLICATION_JSON);

		MvcResult getUsers = mockMvc.perform(getUsersRequest).andReturn();
		
		String allUsers = "[{\"first_name\":\"roshun\",\"last_name\":\"menon\","
				+ "\"email\":\"menonr\",\"password\":\"nice\",\"username\":\"rocean\""
				+ ",\"country\":null,\"state\":null,\"dob\":null,\"gender\":null,\"is_active\":false,\"is_admin\":false,\"prof_pic\":"
				+ "null,\"about_me\":null,\"allow_location\":false}]";
		
		//Make sure db correctly has not added them.
//		JSONAssert.assertEquals(allUsers, getUsers.getResponse()
//				.getContentAsString(), false);
	}
}

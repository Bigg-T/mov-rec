package edu.northeastern.cs4500.DB.user;

import java.util.Collection;
import java.util.Date;
import javax.validation.constraints.NotNull;

import edu.northeastern.cs4500.DB.movie.UserRatesObject;

import javax.persistence.*;


/**
 * DATA MODEL for USER table in database
 *
 */
@Entity(name="user")
public class UserObject {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

	@NotNull
	private String first_name;
	@NotNull
	private String last_name;
	@NotNull
	@Column(unique=true)
	private String email;
	@NotNull //This should be hashed
	private String password;
	@NotNull
	@Column(unique=true)
	private String username;
	private String country;
	private String state;
	private Date dob;
	private String gender;
	private boolean is_active;
	private boolean is_admin;
	private String prof_pic;
	private String about_me;
	private boolean allow_location;
	
	@JoinTable(name = "user_friends", joinColumns = {
			 @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
			 @JoinColumn(name = "friendId", referencedColumnName = "id", nullable = false)})
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<UserObject> friends;	
	private boolean logged;
	
	/**
	 * Can add any default fields here
	 */
	public UserObject() {
		this.is_admin = false;
	}
	
	/**
	 * Constructor for UserObject,
	 * Can be used in the Registration page
	 * 
	 */
	public UserObject(String first_name, String last_name, String email, String password, String username) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.username = username;
	}
	
	public int getId() {
		return id;
	}
	public boolean isAllow_location() {
		return allow_location;
	}
	public void setAllow_location(boolean allow_location) {
		this.allow_location = allow_location;
	}
	public String getAbout_me() {
		return about_me;
	}
	public void setAbout_me(String about_me) {
		this.about_me = about_me;
	}
	public String getProf_pic() {
		return prof_pic;
	}
	public void setProf_pic(String prof_pic) {
		this.prof_pic = prof_pic;
	}
	public boolean isIs_admin() {
		return is_admin;
	}
	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}
	public boolean isIs_active() {
		return is_active;
	}
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public Collection<UserObject> getFriends() {
		return friends;
	}

	public void setFriends(Collection<UserObject> friends) {
		this.friends = friends;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	
	
}

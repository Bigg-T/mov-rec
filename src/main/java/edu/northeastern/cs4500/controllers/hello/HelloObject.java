package edu.northeastern.cs4500.controllers.hello;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="hello")
public class HelloObject {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String message;
	
	/**
	 * Create a message.
	 */
	public HelloObject() {
		this.message = "";
	}
	
	/**
	 * Create a object with a specific message.
	 * 
	 * @param message the hello message
	 */
	public HelloObject(String message) {
		this.message = message;
	}


	public int getId() {
		return this.id;
	}

	/**
	 * Set the id.
	 * @param id the given id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Read the message.
	 * 
	 * @return the current message
	 */
	public String getMessage( ) {
		return this.message;
	}
	
	/**
	 * Update the message.
	 * @param message the updated message.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}

//package edu.northeastern.cs4500.DB.movie;
//
//import java.util.Set;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
////import java.util.Set;
////import javax.persistence.Entity;
////import javax.persistence.GeneratedValue;
////import javax.persistence.GenerationType;
////import javax.persistence.Id;
////import javax.persistence.ManyToMany;
//
///**
// * The Genres that a movie can have
// * 
// */
//
//@Entity(name="Genre")
//public class GenreObject {
//	
//	
////	@Id
////	@GeneratedValue(strategy=GenerationType.IDENTITY)
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//	public int id;
//	@NotNull
//	private String name;
//	private Set<MovieObject> movies;
//
//	
//	public GenreObject(String name) {
//		this.name = name;
//	}
//	
//	
//	GenreObject() {
//		
//	}
//	
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//	    public int getId() {
//	        return id;
//	    }
//
//	    public void setId(int id) {
//	        this.id = id;
//	    }
//	
//	
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	
//    @ManyToMany(mappedBy = "genres")
//    public Set<MovieObject> getMovies() {
//        return movies;
//    }
//
//    public void setMovies(Set<MovieObject> movies) {
//        this.movies = movies;
//    }
//}

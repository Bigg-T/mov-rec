package edu.northeastern.cs4500.DB.movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieObject, Integer>{

}

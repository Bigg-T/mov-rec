package edu.northeastern.cs4500.DB.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs4500.models.MovieRatingsObject;

@Repository
public interface MovieRepository extends JpaRepository<MovieRatingsObject, Integer>{

}

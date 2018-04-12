package edu.northeastern.cs4500.DB.movie;

import edu.northeastern.cs4500.JPARepositories.MovieRatingRepository;
import edu.northeastern.cs4500.JPARepositories.MovieRepoTN;
import edu.northeastern.cs4500.JPARepositories.UserRepoTN;
import edu.northeastern.cs4500.models.MovieRatingsObject;
import edu.northeastern.cs4500.rec.ICFAlgo;
import edu.northeastern.cs4500.rec.SimpleSVD;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.northeastern.cs4500.DB.movie.UserRatesObject;
import edu.northeastern.cs4500.DB.user.UserObject;
import edu.northeastern.cs4500.JPARepositories.UserRatesRepository;
import edu.northeastern.cs4500.JPARepositories.UserRepository;

@CrossOrigin(origins = {"http://m0vi3h4ll.s3-website.us-east-2.amazonaws.com",
    "http://localhost:3000"})
@RestController
public class UserRatesController {

    @Autowired
    UserRatesRepository userRatesRepository;

    @Autowired
    UserRepository userRepository;

  @Autowired
  MovieRepoTN movieRepoTN;
  @Autowired
  MovieRatingRepository movieRatingsRepository;

  @Autowired
  UserRepoTN userRepoTN;



//    @RequestMapping("/api/test/rate")
//    public List<UserRatesObject> getAllUserRates() {
//      return userRatesRepository.getOnlyUserWatchedRate();
////      return userRatesRepository.getAllUserRating();
//    }
//    @RequestMapping("/api/dev/varRate")
//    public List<UserRatesObject> getDevUM(Integer user_id, Integer movie_id) {
//
////      return userRatesRepository.getReccomendedMovieforUser(user_id);
//      return userRatesRepository.getRateByUserIdMoiveId(user_id,movie_id,true);
////      return temp;
//    }
//    @RequestMapping("/api/test/avg/") // dev testing
//    public Double getMovieAvgRate(Integer movie_id) {
//      return userRatesRepository.getRatingWithMovieId(movie_id);
//    }
////
//    @RequestMapping("/api/rate/ids/")
//    public List<UserRatesObject> getUserRates(Integer user_id) {
//      return userRatesRepository.getReccomendedMovieforUser(user_id);
//    }
//
//    @RequestMapping("/api/mvs/")
//    public List<Integer> getMovies() {
//      return movieRepoTN.getAllMovies();
//    }
//
//    @RequestMapping("/api/userIds/")
//    public List<Integer> getAllIds(){
//      return userRepoTN.getAllIDUser();
//    }

    @GetMapping("/api/movie/addUserRates/")
    public Map<String, Object> addUserRates(Integer movie_id, Integer user_id, Double rate) {
      //check if user is logged in and exists
      HashMap<String, Object> userRatesData = new HashMap<String, Object>();
      Boolean isLoggedIn = false;
      if (movie_id == null || user_id == null || rate == null) {
        userRatesData.put("isSuccess", false);
        userRatesData.put("message", "Incorrect Input");
        userRatesData.put("status", HttpStatus.BAD_REQUEST);
        return userRatesData;
      }
      UserObject user;
      try {
        user = userRepository.getOne(user_id);
        user.getEmail();// indirectly check for user exist

        //delete previous exist
        List<UserRatesObject> temp = userRatesRepository.getRateByUserIdMoiveId(user_id, movie_id,true);
        temp.forEach(u -> userRatesRepository.delete(u));
        //delete previous exist rating from algorithm
        List<UserRatesObject> temp2 = userRatesRepository.getRateByUserIdMoiveId(user_id, movie_id,false);
        temp2.forEach(u -> userRatesRepository.delete(u));

        UserRatesObject obj = new UserRatesObject(movie_id, user_id,rate);
        obj.setRate(rate);
        userRatesRepository.save(obj);
        userRatesData.put("isSuccess", true);
        userRatesData.put("status", HttpStatus.OK);
        userRatesData.put("userRatingId", obj.getId());
        return userRatesData;
      } catch(Exception e) {
        userRatesData.put("isSuccess", false);
        userRatesData.put("message", "User Does Not Exist");
        userRatesData.put("status", HttpStatus.NOT_FOUND);
        return userRatesData;
      }
    }

    @RequestMapping("/api/movie/getRecommendedMovies/")
    public Map<String, Object> getTopMovies(Integer user_id) {
      Map<String, Object> userRatesData = new HashMap<>();
      List<UserRatesObject> userTopMovies = userRatesRepository.getReccomendedMovieforUser(user_id);
      userRatesData.put("isSuccess", true);
      userRatesData.put("status", HttpStatus.OK);
      userRatesData.put("topMovies", userTopMovies);
      return userRatesData;
    }

    @GetMapping("/api/movie/calculateRec/")
    public Map<String, Object> getCalculated() {
      //delete previous exist
      ExecutorService executor = Executors.newFixedThreadPool(50);
      Runnable delete = () -> userRatesRepository.deleteAllRec();
      executor.execute(delete);
      //setup
      List<Integer> tmdbMovieID = movieRepoTN.getAllMovies();
      List<UserRatesObject> userWatchedRateObjectList = userRatesRepository.getOnlyUserWatchedRate();
      List<Integer> userIDList = userRepoTN.getAllIDUser();
      //UserItem
      double[][] userItem = new double[userIDList.size()][tmdbMovieID.size()];
      RealMatrix realMatrix = new Array2DRowRealMatrix(userItem);
      userItem = realMatrix.scalarAdd(1).getData();
      //SetUp Map for indexing to user Item

      //Mapping of the position (row) or the y direction are user
      // <User_ID, PositionIn2DArray>
      Map<Integer, Integer> userId2Pos = mapId2Pos(userIDList);
      //Mapping of the position (column) or the x direction are movie id
      // <PositionIn2DArray, Movie_ID>
      Map<Integer, Integer> movieId2Pos = mapId2Pos(tmdbMovieID);

      ////userID, Set of Movie_ids watched
      Map<Integer, Set<Integer>> watchedMovieIds = new TreeMap<>();

      for(int i = 0; i < userWatchedRateObjectList.size(); i++) {
        UserRatesObject rateObject = userWatchedRateObjectList.get(i);
        int movieId = rateObject.getMovie_id();
        int userId = rateObject.getUser_id();
        if(userId2Pos.containsKey(userId)
            && movieId2Pos.containsKey(movieId)) {
          int row = userId2Pos.get(userId);
          int col = movieId2Pos.get(movieId);
          userItem[row][col] = rateObject.getRate();
          if (watchedMovieIds.containsKey(userId)) {
            watchedMovieIds.get(userId).add(movieId);
          } else {
            Set<Integer> mids = new TreeSet<>();
            mids.add(movieId);
            watchedMovieIds.put(userId,mids);
          }
        }
      }

      //get the prediction
      ICFAlgo algo = new SimpleSVD(userItem,.65);
      double[][] prediction = algo.compute();

      for (int user = 0; user < userIDList.size(); user++) {
        int userID = userIDList.get(user);
        int uPos = userId2Pos.get(userID);
        for(int item = 0; item < tmdbMovieID.size(); item++) {
          int itemID = tmdbMovieID.get(item);
          int iPos = movieId2Pos.get(itemID);
          if(watchedMovieIds.keySet().contains(userID)
              && !watchedMovieIds.get(userID).contains(itemID)) {
            double pRate = prediction[uPos][iPos];
            // rating
            UserRatesObject ratesObject =
                new UserRatesObject(itemID, userID, pRate, false);
            Runnable temp = () -> userRatesRepository.save(ratesObject);
            executor.submit(temp);
          }
        }
      }
      Map<String, Object> temp = new HashMap<>();
      temp.put("rates",prediction);
      temp.put("movies", tmdbMovieID);
      temp.put("user", userIDList);
      temp.put("isSuccess", true);
      return temp;
    }

    private Map<Integer, Integer> mapId2Pos(List<Integer> id) {
      Map<Integer,Integer> temp = new TreeMap<>();
      for (int i = 0; i < id.size(); i++) {
        temp.put(id.get(i),i);
      }
      return temp;
    }

  }
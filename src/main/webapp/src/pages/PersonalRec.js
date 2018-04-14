/**
 * Created by t on 4/12/18.
 */

import React, {Component} from 'react';
import {Card, Container, Button} from 'semantic-ui-react';
import MovieCard from '../components/MovieCard';
import axios from 'axios';
import * as constant from '../config';
import {genTMDBMovie, genMovieVid, genImageURL3} from '../MovieDBConstant';
import _ from 'lodash';
class PersonalRec extends Component {

  constructor(props) {
    super(props);
    this.state = {
      movies : []
    };
    this.itemPerRow = 3;
  }

  componentDidMount() {
    const UM = "https://api.themoviedb.org/3/movie/201/lists?api_key=492a79d4999e65c2324dc924891cb137&language=en-US&page=1";
    const UM2 = 'http://localhost:8081/api/movie/popular/?num=6';
    let TMDB = 'https://api.themoviedb.org/3/movie/157336?api_key={api_key}';
    let API = 'api/movie/getRecommendedMovies/?user_id='+window.localStorage['user_id'];
    let API_END = constant.MOVI3HALL_BASE_API + API;
    let data = axios.get(API_END).then(res => {
      let dataLen = res.data.length;
      let len = dataLen < 20 ? dataLen : 20;
      // console.log(res.data);
      let myMov = res.data.topMovies;
      // return myMov.slice(0, len).map(userRateObj => {
      //   axios.get(genTMDBMovie(userRateObj.movie_id)).then(tmdbRes => {
      //     // console.log(tmdbRes.data);
      //     this.state.movies.push(tmdbRes.data);
      //     return tmdbRes.data;
      //   });
      // });
      let mov20 = myMov.slice(0, len);
      this.setState({movies:mov20});
      // return myMov.slice(0, len);
    });

    // this.state.movies.map(m => {
    //   console.log(m.title)
    // });

    console.log(data);
    console.log(this.state.movies);
    // let movieIds = data.map(rateObj => rateObj.movie_id);
    //const MDB_API_KEY = '492a79d4999e65c2324dc924891cb137';
    const BASE_MDB_URL = 'https://api.themoviedb.org/3/';
    //let URL = BASE_MDB_URL+'movie/popular?api_key='+MDB_API_KEY + '&language=en-US&page=1';
    console.log("MOVE");
    // console.log(movieIds);
  }
  /**
   * Created a list cards with the correct information.
   */
  renderRow() {
    let row = [];
    this.state.movies.map(mObj => {
      row.push(<SCard key={mObj.movie_id} movieID={mObj.movie_id} />)
    });
    return row;
  }

  render() {
    return (
    <Container>
      <Card.Group itemsPerRow={this.itemPerRow} stackable>
        {this.renderRow()}
      </Card.Group>
    </Container>
    );
  }
}

class SCard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      sCard : ''
    }
  }

  handleDismiss(movieID) {
    let userID = window.localStorage['user_id'];
    let API = 'api/movie/dismiss/?user_id='+userID+'&movie_id='+movieID;
    let API_REST = constant.MOVI3HALL_BASE_API+API;
    axios.get(API_REST).then(res => console.log(res.data));
  }
  componentDidMount() {
    let movieID = this.props.movieID;

    axios.get(genTMDBMovie(movieID)).then(tmdbRes => {
      let tb = tmdbRes.data;
      console.log(tb);
      let baseURL = 'https://image.tmdb.org/t/p/w300/';
      let names = tb.title;
      let ids = tb.id;
      let imageURL = tb.poster_path;
      let vote_average = tb.vote_average;
      let release_date = tb.release_date;
      let overview = _.truncate(tb.overview, {'length': 50, 'separator': ' '});


      let mCard = (
          <Card>
            <Button content='Dismiss Recommendation' color='orange' onClick={() => this.handleDismiss(ids)}/>
            <MovieCard key={ids} movieName={names} id={ids}
                       movieURL={genImageURL3(imageURL)}
                       movieOverview={overview}
                       rate={vote_average}
                       year={release_date}
                       movieJSON={tb}/>
          </Card>);

      // this.state.movies.push(tmdbRes.data);

      this.setState({sCard : mCard});
      // return tmdbRes.data;
    });

  }
  render() {
    return this.state.sCard;
  }
}

export default PersonalRec;
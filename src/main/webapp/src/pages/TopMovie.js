/**
 * Created by t on 4/15/18.
 */
import React, {Component} from 'react';
import {Card, Container, Button} from 'semantic-ui-react';
import WLCard from '../components/WLCard';
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
    let API = 'api/movie/getTopMovies/?user_id='+window.localStorage['user_id']+'&limit=3';
    let API_END = constant.MOVI3HALL_BASE_API + API;
    let data = axios.get(API_END).then(res => {
      console.log(res.data);
      let myMov = res.data.movies;
      this.setState({movies:myMov});
    });

    console.log(data);
    console.log(this.state.movies);
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

  componentDidMount() {
    let movieID = this.props.movieID;

    axios.get(genTMDBMovie(movieID)).then(tmdbRes => {
      let tb = tmdbRes.data;
      console.log(tb);
      let names = tb.title;
      let ids = tb.id;
      let imageURL = tb.poster_path;
      let vote_average = tb.vote_average;
      let release_date = tb.release_date;
      let overview = _.truncate(tb.overview, {'length': 50, 'separator': ' '});

      let mCard = (
        <WLCard key={ids} movieName={names} id={ids}
                   movieURL={genImageURL3(imageURL)}
                   movieOverview={overview}
                   rate={vote_average}
                   year={release_date}
                   movieJSON={tb}/>
      );
      this.setState({sCard : mCard});
    });

  }
  render() {
    return this.state.sCard;
  }
}

export default PersonalRec;
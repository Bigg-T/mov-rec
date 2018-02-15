/**
 * Created by t on 2/13/18.
 */
import React, {Component} from 'react';
import axios from 'axios';
import {Card, Header} from 'semantic-ui-react';
import MovieCard from './MovieCard';
import _ from 'lodash';

class MovieSection extends Component {
  constructor(props) {
    super(props);
    this.state = {
      myData : [],
    }
  }

  componentDidMount() {
    const UM = "https://api.themoviedb.org/3/movie/201/lists?api_key=492a79d4999e65c2324dc924891cb137&language=en-US&page=1";
    const UM2 = 'https://api.themoviedb.org/3/movie/popular?api_key=492a79d4999e65c2324dc924891cb137&language=en-US&page=1';
    const MDB_API_KEY = '492a79d4999e65c2324dc924891cb137';
    const BASE_MDB_URL = 'https://api.themoviedb.org/3/';
    let URL = BASE_MDB_URL+'/movie/popular?api_key='+MDB_API_KEY + '&language=en-US&page=1';

    axios.get(UM2)
    .then(res => {
      const data = res.data;
      console.log('MyData');
      console.log(data.results);
      this.setState({myData : data.results });
    })
  }

  renderRow(cardNum) {
    let baseURL = 'https://image.tmdb.org/t/p/w300/';
    let names = this.state.myData.map(obj => obj.title);
    let imageURL = this.state.myData.map(obj => obj.poster_path);
    let overview = this.state.myData.map(obj => obj.overview);
    let vote_average = this.state.myData.map(obj => obj.vote_average);
    let release_date = this.state.myData.map(obj => obj.release_date);
    let row = [];
    console.log("overvieww");
    let a = _.truncate(overview[0], {
      'length': 50,
      'separator': ' '
    });
    console.log(a.substring(1,5));
    for (let i = 0; i < cardNum; i++) {
      let a = _.truncate(overview[0], {
        'length': 100,
        'separator': ' '
      });
      row.push(<MovieCard key={i} movieName={names[i]}
                          movieURL={baseURL+imageURL[i]}
                          movieOverview={a}
                          rate = {vote_average[i]} year={release_date[i]}/>)
    }
    console.log(this.state.myData[0]);
    // console.log(NODE);
    console.log(this.state.myData);
    return row;
  }

  render() {
    return (
        <div >
          <div className="myheader">
            <Header content={this.props.sectionName} as='h1'/>
          </div>
          <Card.Group itemsPerRow={this.props.perCol}>
            {this.renderRow(this.props.perCol*this.props.perRow)}
          </Card.Group>
        </div>
    );
  }

}

export default MovieSection;
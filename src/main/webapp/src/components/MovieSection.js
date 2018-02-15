/**
 * Created by t on 2/13/18.
 */
import React, {Component} from 'react';
import axios from 'axios';
import {Card, Header} from 'semantic-ui-react';
import MovieCard from './MovieCard';

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
    let names = this.state.myData.map(obj => obj.title);
    let row = [];
    // console.log(row);
    for (let i = 0; i < cardNum; i++) {
      row.push(<MovieCard key={i} movieName={names[i]/* Movie name*/}/>)
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
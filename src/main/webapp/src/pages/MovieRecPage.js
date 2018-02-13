/**
 * Created by t on 2/11/18.
 */
import React, {Component} from 'react';
import {Grid, Header, Container} from 'semantic-ui-react';
import MovieCard from '../components/MovieCard';
import '../css/MovieRecPage.css';

class MovieRecPage extends Component {

  render() {
    return (
        <div className="mysection">
          <Container>
            <MovieSection sectionName={"What's Hot"}/>
          </Container>
          <Container>
            <MovieSection sectionName={"Action"}/>
          </Container>
          <Container>
            <MovieSection sectionName={"Comedy"}/>
          </Container>
        </div>
    );
  }
}

class MovieSection extends Component {
  constructor(props) {
    super(props);
    this.state = {
      myData : "",
    }
  }
  async fetchAsyc(url) {
    let data = await (await fetch(url,{
      method: `GET`,
      headers: {
      }
    })).json();
    this.state.myData = data;
    // console.log(data);

  }

  renderRow(cardNum) {
    const UM = "https://api.themoviedb.org/3/movie/201/lists?api_key=492a79d4999e65c2324dc924891cb137&language=en-US&page=1";
    const MDB_API_KEY = '492a79d4999e65c2324dc924891cb137';
    const BASE_MDB_URL = 'https://api.themoviedb.org/3/';
    let URL = BASE_MDB_URL+'movie/20/lists?page=1&language=en-US&api_key='+MDB_API_KEY;
    let temp = "";
    // let myJson = this.fetchAsyc(URL);
    let myJson = fetch(URL,{
      method: `GET`,
      headers: {
      }
    })
    .then((resp) => resp.json())
    .then(function(data) {
      // console.log(data.results);
      // temp = data.results;
      return data.results;
      // return results.json();
    });


    console.log("before");

    let row = [];
    let yo = myJson.then((d) => {
      // console.log(d);
      console.log("start");
      console.log(row.length);
      for (let i = 0; i < cardNum; i++) {
        // console.log(d[i].description);
        //need to use json int the future
        // row.push(d[i].description);
        {/*row.push(<Grid.Column key={i}><MovieCard key={i} movieName={i/* Movie name*!//></Grid.Column>)*/}
      }
      console.log("stop");
      console.log(row.length);

      console.log(row);
    });

    console.log("after");
    console.log(row.length);

    // console.log(row);
    for (let i = 0; i < cardNum; i++) {
      //need to use json int the future
      row.push(<Grid.Column key={i}><MovieCard key={i} movieName={i/* Movie name*/}/></Grid.Column>)
    }
    console.log(row);

    return row;
  }

  render() {
    return (
        <div >
          <div className="myheader">
          <Header content={this.props.sectionName} as='h1'/>
          </div>
          <Grid columns={4}>
            {this.renderRow(4)}
          </Grid>
        </div>
    )
  }
}

export default MovieRecPage;
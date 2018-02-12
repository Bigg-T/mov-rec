/**
 * Created by t on 2/11/18.
 */
import React, {Component} from 'react';
import {Grid, Header, Container, Divider} from 'semantic-ui-react';
import MovieCard from './MovieCard';
import MyNav from './MyNav';

import './MovieRecPage.css';

class MovieRecPage extends Component {
  render() {
    return (
        <div className="mysection">
          <MyNav/>
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

  renderRow(cardNum) {
    let row = [];
    for (let i = 0; i < cardNum; i++) {
      //need to use json int the future
      row.push(<Grid.Column><MovieCard movieName={i/* Movie name*/}/></Grid.Column>)
    }
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
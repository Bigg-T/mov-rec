/**
 * Created by t on 2/11/18.
 */
import React, {Component} from 'react';
import {Grid, Header, Container, Card} from 'semantic-ui-react';
import '../css/MovieRecPage.css';
import MovieSection from '../components/MovieSection';

class MovieRecPage extends Component {

  render() {
    return (
        <div>
          <div className="mysection">
            <Container>
              <MovieSection sectionName={"What's Hot"} perRow={2} perCol={3}/>
            </Container>
            <Container>
              <MovieSection sectionName={"Action"} perRow={2} perCol={3} />
            </Container>
            <Container>
              <MovieSection sectionName={"Comedy"} perRow={2} perCol={3} />
            </Container>
          </div>
        </div>
    );
  }
}



export default MovieRecPage;
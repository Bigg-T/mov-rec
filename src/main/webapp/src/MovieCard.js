/**
 * Created by t on 2/10/18.
 */
import React, {Component} from 'react';
import {Rating, Card} from 'semantic-ui-react'

class MovieCard extends Component {
  render() {
    return (
        <div>
          <Rating rating={1} maxRating={5} />
          <Card description={"A movie"} header={"MOVIE"} />
          <div>
            {this.props.value}
          </div>
        </div>
    );
  }
}

export default MovieCard;
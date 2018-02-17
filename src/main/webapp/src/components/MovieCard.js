/**
 * Created by t on 2/10/18.
 */
import React, {Component} from 'react';
import {Card, Image, Grid} from 'semantic-ui-react'
import {Badge, Glyphicon} from 'react-bootstrap';

class MovieCard extends Component {

  render() {
    return (
        <Card>
          <Card.Content>
            <Card.Header>
              {this.props.movieName}
            </Card.Header>
            <Card.Meta>
              <Grid columns="equal">
                <Grid.Column width={10}>
                  <span className="date">
                    Released on {this.props.year} {this.props.releaseDate}
                  </span>
                </Grid.Column>
                <Grid.Column>
                  <Badge>{this.props.rate}/10 <Glyphicon glyph="star" /></Badge>
                </Grid.Column>
              </Grid>
            </Card.Meta>
            <Card.Description>
              {this.props.movieOverview}
            </Card.Description>
          </Card.Content>
          <Image src={this.props.movieURL}/>

        </Card>
    );
  }
}


export default MovieCard;
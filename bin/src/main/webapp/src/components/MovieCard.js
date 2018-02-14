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
              Movie Name Holder {this.props.movieName}
            </Card.Header>
            <Card.Meta>
              <Grid columns="equal">
                <Grid.Column width={12}>
                  <span className="date">
                    Released on 2016 {this.props.releaseDate}
                  </span>
                </Grid.Column>
                <Grid.Column>
                  <Badge>4/10 {this.props.rate} <Glyphicon glyph="star" /></Badge>
                </Grid.Column>
              </Grid>
            </Card.Meta>
            <Card.Description>
              This movie is all about JP.
              {this.props.movieDescription}
            </Card.Description>
          </Card.Content>
          <Image src="https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAWxAAAAJGFiMTBjY2M5LTI4ZTAtNDNlOC04NTczLTI3ZmI1OThhMTAyZQ.jpg"/>

        </Card>
    );
  }
}

export default MovieCard;
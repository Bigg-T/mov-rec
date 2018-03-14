/**
 * Created by t on 2/10/18.
 */
import React, {Component} from 'react';
import {Card, Image, Grid} from 'semantic-ui-react'
import {Badge, Glyphicon} from 'react-bootstrap';
import {Route, Redirect} from 'react-router-dom';

class MovieCard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isRedirect : false
    }
  }

  handleClick() {
    this.setState({isRedirect : true})
  }
  render() {
    return (
        <div>
          <div>
            <Route exact path="/" render={() => (
                this.state.isRedirect ? (
                        <Redirect to={{
                          pathname: '/movie/'+this.props.id,
                          state: { referrer: this.props.movieJSON }
                          state: { referrer: this.state.myData }
                        }}/>
                    ) : (
                        ""
                    )
            )}/>
          </div>
          <Card link={true} onClick={() => this.handleClick()}>
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
        </div>
    );
  }
}


export default MovieCard;

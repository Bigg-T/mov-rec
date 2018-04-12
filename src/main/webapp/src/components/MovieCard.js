/**
 * Created by t on 2/10/18.
 */
import React, {Component} from 'react';
import {Card, Image, Grid,Rating} from 'semantic-ui-react'
import {Badge, Glyphicon} from 'react-bootstrap';
import {Route, Redirect} from 'react-router-dom';
import axios from 'axios';
import * as constant from '../config';

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

  handleRate = (e, { rating, maxRating }) => {
    let user_id = window.localStorage['user_id'];
    let API = 'api/movie/addUserRates/?movie_id='
        +this.props.id+'&user_id='+user_id+'&rate='+rating;
    let API_END = constant.MOVI3HALL_BASE_API + API;
    axios.get(API_END).then(res => {
      console.log(res.data);
    });
    this.setState({ rating, maxRating })
  };
  render() {
    let rate = (window.localStorage['isLoggedIn']?
        (
        <Grid.Row>
          <Grid.Column width={8}>
            What would you rate this?
          </Grid.Column>
          <Grid.Column>
            <Rating maxRating={4} onRate={this.handleRate} />
          </Grid.Column>
        </Grid.Row>
        )
        :
        "");
    return (
        <div>
          <div>
            <Route path="/" render={() => (
                this.state.isRedirect ? (
                        <Redirect to={{
                          pathname: '/movie/'+this.props.id,
                          state: { referrer: this.props.movieJSON }
                        }}/>
                    ) : (
                        ""
                    )
            )}/>
          </div>
          <Card>
            <Card.Content>
              <Card.Header>
                {this.props.movieName}
              </Card.Header>
              <Card.Meta>
                <Grid columns="equal">
                  <Grid.Row>
                    <Grid.Column width={10}>
                      <span className="date">
                        Released on {this.props.year} {this.props.releaseDate}
                      </span>
                    </Grid.Column>
                    <Grid.Column>
                      <Badge>{this.props.rate}/10 <Glyphicon glyph="star" /></Badge>
                    </Grid.Column>
                  </Grid.Row>
                  {rate}
                </Grid>
              </Card.Meta>
              <Card.Description>
                {this.props.movieOverview}
              </Card.Description>
            </Card.Content>
            <Image as="a" src={this.props.movieURL} onClick={() => this.handleClick()}/>
          </Card>
        </div>
    );
  }
}


export default MovieCard;
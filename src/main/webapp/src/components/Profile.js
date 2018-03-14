
import React, {Component} from 'react';
import {Item, Image, Grid, Rating, Container} from 'semantic-ui-react';
import {Badge, Glyphicon, Label} from 'react-bootstrap';
import { View, Text, AppRegistry } from 'react';
import {genImageURL5} from '../MovieDBConstant';
import axios from 'axios';
import {genMovieVid} from '../MovieDBConstant';
import YouTube from './YouTube';
import '../css/Profile.css';

  //import {View} from 'react-native';


  class Profile extends Component {
    constructor(props) {
      super(props);
//      this.state = {
//        youtube : [''],
//      }
    }
    componentWillMount() {
//      let movieID = this.props.location.state.referrer.id;//
//      axios.get(genMovieVid(movieID))
//      .then(res => {
//        if (res.data.results.length != 0) {
//          let data = res.data.results[0];
//          console.log('KEYYYYYYY');
//          console.log(data);
//          this.setState({youtube : [data.key]});
//        }
//
//      });

    }
    render() {
      //let YOUTUBE_BASE = 'https://www.youtube.com/watch?v=';
      //console.log("referrer")
//      console.log(this.props.location.state.referrer);
//      let name = this.state.youtube.map(a => a);
      //let YOUTUBE_VID = YOUTUBE_BASE+name[0];
      return (

<Container>
	<Grid>
		<Grid.Column width={4}>
			<Grid.Row>
				<h3>Jean Paul Torre</h3>
			</Grid.Row>
			<Grid.Row>
				Add Friend
			</Grid.Row>
			<Grid.Row width={1}>
				<Image src={"http://www.personalbrandingblog.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png"} />
			</Grid.Row>
			<Grid.Row>About Me</Grid.Row>
			<Grid.Row>
			 <p>"I love movies, which is why I decided to join this website. I would like to meet people to watch movies with"</p>
			 </Grid.Row>
		</Grid.Column>
		<Grid.Column width={10} margin-right={40}>
		<Grid.Row>
		</Grid.Row>
		<Grid.Row>
		<div class="topMovies">
		<div class="topMoviesTitle">
		<h2>Top Movies </h2>
		</div>
		</div>
		</Grid.Row>
		
		</Grid.Column>
	</Grid>
          </Container>
         );

    }
  }

  export default Profile;


import React, {Component} from 'react';
import {Item, Image, Grid, Rating, Container} from 'semantic-ui-react';
import {Badge, Glyphicon, Label} from 'react-bootstrap';
import { View, Text, AppRegistry } from 'react';
import axios from 'axios';
import '../css/Profile.css';

  //import {View} from 'react-native';


  class Profile extends Component {
    constructor(props) {
      super(props);
      this.state = {
        firstName : "",
        lastName : "",
        aboutMe: "",
        profPic: "",
      }
    }
    componentWillMount() {
//    	 let profileId = this.props.params.id;
//    	 let user_request = window.localStorage.getItem("user_id");
    	let user_request = window.localStorage.getItem("user_id");
    	if (user_request != null) {
    	let profileId = this.props.match.params.id;
    	axios.get("http://localhost:8081/api/user/profile/?id=" + profileId + "&user_request="+ user_request)
        .then((code) => {
        	console.log("--------------------");
        	  console.log(window.localStorage.getItem("user_id"));
          console.log(code);
          let isSuccess = code.data.isSuccess;
          if (isSuccess) {
        	  	this.setState({firstName : code.data.first_name, lastName : code.data.last_name})
        	  	if (code.data.about_me == null) {
        	  		this.setState({aboutMe: "Empty"})
        	  	} else {
        	  		this.setState({aboutMe: code.data.about_me})
        	  	}
          }
        });
    }
    }
  
    
    render() {
      return (
<Container>
	<Grid>
		<Grid.Column width={4}>
			<Grid.Row>
				<h3>{this.state.firstName} {this.state.lastName}</h3>
			</Grid.Row>
			<Grid.Row>
				Add Friend
			</Grid.Row>
			<Grid.Row width={1}>
				<Image src={"http://www.personalbrandingblog.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png"} />
			</Grid.Row>
			<Grid.Row>About Me</Grid.Row>
			<Grid.Row>
			 <p>{this.state.aboutMe}</p>
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


import React, {Component} from 'react';
import {Item, Image, Grid, Rating, Container,Button} from 'semantic-ui-react';
import {Badge, Glyphicon, Label} from 'react-bootstrap';
import { View, Text, AppRegistry } from 'react';
import axios from 'axios';
import '../css/Profile.css';
import * as constant from '../config';

  //import {View} from 'react-native';


  class Profile extends Component {
    constructor(props) {
      super(props);
      this.state = {
        firstName : "",
        lastName : "",
        aboutMe: "",
        profPic: "",
        isFriend: false,
        friendButton: "",
        myProfile: true,
        //curUrl: "http://moviehall.us-east-2.elasticbeanstalk.com"
        curUrl: "http://localhost:8081"
      }
    }

    /**
     * Logic for Adding a friend
     */
        handleAddFriend() {
    	    let user_request = window.localStorage.getItem("user_id");
    		if (user_request != null) {
    			let profileId = this.props.match.params.id;
    			axios.post(this.state.curUrl + "/api/user/add_friend/?userId="
          + user_request + "&friendId=" + profileId)
    			.then(function (response) {

    				let isSuccess = response.data.isSuccess;
    				if (isSuccess) {
    					window.alert("Successfully Added Friend");
    					window.location.reload();
    				} else {
    					if (response.data.message != null) {
    						window.alert(response.data.message);
    					} else {
    						window.alert("There was an error :(");
    					}
    				}
    			})
    			.catch(function (e) {
    				window.alert("There was an error :( ");
    			});
    		}
    }

        /**
         * Logic for Removing a friend
         */
        handleDeleteFriend() {
        	let user_request = window.localStorage.getItem("user_id");
        	if (user_request != null) {
        		let profileId = this.props.match.params.id;
        		axios.post(this.state.curUrl +
              "/api/user/remove_friend/?userId=" + user_request + "&friendId=" + profileId)
        		.then(function (response) {
        			let isSuccess = response.data.isSuccess;
        			if (isSuccess) {
        				window.alert("Successfully Removed Friend");
        				window.location.reload(); // for now, see if better alternatives later
        			} else {
        				if (response.data.message != null) {
        					window.alert(response.data.message);
        				} else {
        					window.alert("There was an error :(");
        				}
        			}
        		})
        		.catch(function (e) {
        			window.alert("There was an error :(");
        		});
        	}
        }
    componentWillMount() {
//    	 let profileId = this.props.params.id;
//    	 let user_request = window.localStorage.getItem("user_id");
    	let user_request = window.localStorage.getItem("user_id");
    	if (user_request != null) {
    	let profileId = this.props.match.params.id;
    	console.log("profileId: " + profileId);
    	console.log("user_request: " + user_request);
    	axios.get(this.state.curUrl +
        "/api/user/profile/?id=" + profileId + "&user_request="+ user_request)
        .then((code) => {
        	console.log("--------------------");
        	  console.log(window.localStorage.getItem("user_id"));
          console.log(code);
          let isSuccess = code.data.isSuccess;
          if (isSuccess) {
        	  	this.setState({firstName : code.data.first_name, lastName : code.data.last_name,
        	  		isFriend: code.data.isFriend, myProfile: code.data.myProfile})
        	  	if (code.data.about_me == null) {
        	  		this.setState({aboutMe: "Empty"})
        	  	} else {
        	  		this.setState({aboutMe: code.data.about_me})
        	  	}
          }
        });
    }
    }

    handleDeleteAccount() {
			let userId = window.localStorage['user_id'];
			let API = '/api/delete/user/?user_id='+userId;
			axios.post(constant.MOVI3HALL_BASE_API+API).then(res => {
				let isSuc = res.data.isSuccess;
				if(isSuc) {
					window.localStorage.clear();
					window.localStorage['isLoggedIn'] = false;
          window.location.replace('/');
				}
			})
		}

    render() {

    	if (this.state.isFriend) {
    		this.state.friendButton = <a href="#" onClick={() => this.handleDeleteFriend()}> Delete Friend </a>;
    	} else {
    		if (this.state.myProfile) {
    			//console.log(this.myProfile);
    			this.state.friendButton = <a href="/profile_edit"> Edit Profile </a>;
    		} else {
        		this.state.friendButton = <a href="#" onClick={() => this.handleAddFriend()}> Add Friend </a>;

    		}
    	}
      return (
<Container>
	<Grid>
		<Grid.Column width={4}>
			<Grid.Row>
				<h3>{this.state.firstName} {this.state.lastName}</h3>
			</Grid.Row>
			<Grid.Row>
			{this.state.friendButton}
			</Grid.Row>
			<Grid.Row width={1}>
				<Image src={"http://www.personalbrandingblog.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png"} />
			</Grid.Row>
			<Grid.Row>About Me</Grid.Row>
			<Grid.Row>
			 <p>{this.state.aboutMe}</p>
			 </Grid.Row>
			<Grid.Row>
				<Button onClick={() => this.handleDeleteAccount()} color="red">Delete My Account</Button>
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

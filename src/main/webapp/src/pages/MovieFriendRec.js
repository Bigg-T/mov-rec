/**
 * Created by t on 3/31/18.
 */

import React, {Component} from 'react';
import axios from 'axios';
import * as constant from '../config';
import {NavLink} from 'react-router-dom';
import {Container, List,Icon, Button} from 'semantic-ui-react';


class MovieFriendRec extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
      isFriendAdd: false,
      myFriend: new Map(),
    };
    this.BASE_API = constant.MOVI3HALL_BASE_API;
    this.BASE_URL = constant.MOVI3HALL_BASE_URL;
  };

  componentDidMount() {
    // let BASE_API = constant.MOVI3HALL_BASE_API;
    let user_request = window.localStorage.getItem("user_id");
	let movie_id = this.props.match.params.movie_id;


    let all_user = '/api/user/recommend/get_friends/?userId=' + user_request + '&movieId=' + movie_id;
    axios.get(this.BASE_API+all_user)
    .then(res => {
      let users = res.data;
      let userFriends = users.friends;
      
      this.setState({data:res.data, myFriend: userFriends})
    });
  }
  
  handleClickAdd(userId, friendId, movieId) {
    let rec_movie = 'api/user/recommend/?userId='+ userId+ '&friendId='+friendId + "&movieId=" + movieId;
   axios.get(this.BASE_API+rec_movie)
    .then(res => {
      let isSuccess = res.data.isSuccess;
      if (isSuccess) {
         window.alert("Movie Was Successfuly recommended");
      } else {
        window.alert("Error " + res.data.status);
      }
      window.location.reload();
	 // window.alert("Button was clicked");
    });
  }
  
  render() {
    let users = [];
    let object = [];
    let allFriends = this.state.data.friends;
    
    let test = this.state.data.friends;
    if (this.state.data.length == 0) {
    		console.log("EMPTY!!!");
    } else {
    	for (let i = 0; i < allFriends.length; i++) {
    		let u = allFriends[i];
          object = (
          <List.Item key={u.id}>
            <Icon name="plus square"/>
            <Button onClick={() => this.handleClickAdd(
                window.localStorage['user_id'], u.id, this.props.match.params.movie_id)}
                    color="blue" key={u.id}>
              Recommend Movie
            </Button>
            <List.Content>
              <List.Header >
                <NavLink to={'/profile/'+u.id}>
                  <h4>
                    {u.name}
                  </h4>
                </NavLink>
              </List.Header>
              <List.Description>
                {u.about_me}
              </List.Description>
            </List.Content>
          </List.Item>
      );
          users.push(object);

    	}
    }
   
    return (
        <Container>
        <h2>{this.props.match.params.movie_title} </h2>
        <h3> Recommend Movie To: </h3>
          <List relaxed celled={true}>
            {users}
          </List>
        </Container>
    );
  }
}

export default MovieFriendRec;

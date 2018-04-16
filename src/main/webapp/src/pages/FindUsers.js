/**
 * Created by t on 3/31/18.
 */

import React, {Component} from 'react';
import axios from 'axios';
import * as constant from '../config';
import {NavLink} from 'react-router-dom';
import {Container, List,Icon, Button} from 'semantic-ui-react';


class FindUsers extends Component {
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
    let all_user = 'api/user/all_users';
    axios.get(this.BASE_API+all_user)
    .then(res => {
      let users = res.data;
      let myfriendList = users.find(aUser => {
        return aUser.id == window.localStorage['user_id']
      }).friends;
      let friendsMap = new Map();
      friendsMap.set(parseInt(window.localStorage['user_id']), parseInt(window.localStorage['user_id']));
      myfriendList.forEach(function(aFriend) {
        friendsMap.set(aFriend.id, aFriend.id);
      });
      this.setState({data:res.data, myFriend:friendsMap});
    });
  }
  handleClickAdd(userId, friendId, friendName) {
    let add_friend = 'api/user/add_friend/?userId='+ userId+ '&friendId='+friendId;
    axios.post(this.BASE_API+add_friend)
    .then(res => {
      let isSuccess = res.data.isSuccess;
      if (isSuccess) {
        // window.alert("Added " + friendName + " as friend");
      } else {
        window.alert("Error " + res.data.status);
      }
      window.location.reload();
    });
  }

  handleClickDelete(userId, friendId, friendName) {
    let add_friend = 'api/user/remove_friend/?userId='+ userId+ '&friendId='+friendId;
    axios.post(this.BASE_API+add_friend)
    .then(res => {
      let isSuccess = res.data.isSuccess;
      if (isSuccess) {
        // window.alert("Removed " + friendName + " as from friend list.");
      } else {
        window.alert("Error " + res.data.status);
      }
      window.location.reload();
    });
  }
  render() {
    let users = [];
    this.state.data.map(u => {
      let object = [];
      if (!this.state.myFriend.has(u.id)) {
        object = (
            <List.Item key={u.id}>
              <Icon name="plus square"/>
              <Button onClick={() => this.handleClickAdd(
                  window.localStorage['user_id'], u.id, u.first_name)}
                      color="blue" key={u.id}>
                Add Friend
              </Button>
              <Button color="red" onClick={() => this.handleClickDelete(
                  window.localStorage['user_id'], u.id, u.first_name)} key={u.id}>
                Delete Friend
              </Button>
              <List.Content>
                <List.Header >
                  <NavLink to={'/profile/'+u.id}>
                    <h4>
                      {u.first_name + ' ' + u.last_name}
                    </h4>
                  </NavLink>
                </List.Header>
                <List.Description>
                  {u.about_me}
                </List.Description>
              </List.Content>
            </List.Item>
        );
      } else {
        object = (
            <List.Item key={u.id}>
              <Icon name="plus square"/>
              <Button color="blue" key={u.id}>
                Already Friended
              </Button>
              <Button color="red" onClick={() => this.handleClickDelete(
                  window.localStorage['user_id'], u.id, u.first_name)} key={u.id}>
                Delete Friend
              </Button>
              <List.Content >
                <List.Header >
                  <NavLink to={'/profile/'+u.id}>
                    <h4>
                      {u.first_name + ' ' + u.last_name}
                    </h4>
                  </NavLink>
                </List.Header>
                <List.Description>
                  {u.about_me}
                </List.Description>
              </List.Content>
            </List.Item>);
      }

      users.push(object);
    });
    return (
        <Container>
          <List relaxed celled={true}>
            {users}
          </List>
        </Container>
    );
  }
}

export default FindUsers;

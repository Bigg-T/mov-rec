/**
 * Created by t on 2/10/18.
 */

import React, {Component} from 'react';
import {Route, Redirect,NavLink} from 'react-router-dom';
import {Navbar, NavItem, Nav} from 'react-bootstrap';
import * as util from '../utils/LogOut';
import axios from 'axios';
import * as constant from '../config';
class MyNav extends Component {
	
  constructor(props) {
	let userIsLoggedIn = window.localStorage.getItem("isLoggedIn");
	if (userIsLoggedIn != "true") {
		userIsLoggedIn = false
	}
	  console.log("--------LOCAL STORAGE NAV BAR-----------");
	  console.log(window.localStorage.getItem("isLoggedIn") == "true");
	  console.log(window.localStorage.getItem("isLoggedIn") == true);
	  console.log(userIsLoggedIn);
	  console.log(window.localStorage.getItem("user_id"))
	  console.log(window.localStorage.getItem("isLoggedIn"));
	  console.log("--------LOCAL STORAGE NAV BAR-----------");
    super(props);
    this.state = {
      isLoggedIn : userIsLoggedIn,
    }
  }
  render() {
    console.log(this.state.isLoggedIn);
    if (this.state.isLoggedIn == "true") {
      return <LoggedInNav />;
    } else {
      return <BasicNav />;
    }
  }
}

class BasicNav extends Component {
  render() {
    return (
        <Navbar inverse collapseOnSelect>
          <Navbar.Header>
            <Navbar.Brand>
              <NavLink to={`/`}><h2>Movi3Hall</h2></NavLink>
            </Navbar.Brand>
            <Navbar.Toggle />
          </Navbar.Header>
          <Navbar.Collapse>
            <Nav pullRight>
              <Navbar.Brand eventKey={1} >
                <NavLink to={`/signin`}><h4>Sign In</h4></NavLink>
              </Navbar.Brand>
              <Navbar.Brand eventKey={1}>
                <NavLink to={`/signup`}><h4>Register</h4></NavLink>
              </Navbar.Brand>
            </Nav>
          </Navbar.Collapse>
        </Navbar>
    );
  }
}

class LoggedInNav extends Component {
	  constructor(props) {
		    super(props);
		    this.state = {
		      user: '/profile/' + window.localStorage.getItem("user_id"),
          redirLogout: "",

		    }
		  }
  onClickLogout() {
    let ENDPOINT = 'api/user/validate_logout/?';
    let userId = window.localStorage['user_id'];
    let REST = constant.MOVI3HALL_BASE_API+ENDPOINT+'user_request='+userId;
    axios.get(REST).then(res => {
      console.log(res.data);

      window.localStorage.clear();
      window.localStorage['isLoggedIn'] = false;
      this.setState({redirLogout:"", isRedir:true});
      window.location.reload();
      return res.data;
    });
  }

  render() {
    return (
              <Navbar inverse collapseOnSelect>
                <Navbar.Header>
                  <Navbar.Brand>
                    <NavLink to={`/`}><h2>Movi3Hall</h2></NavLink>
                  </Navbar.Brand>
                  <Navbar.Toggle />
                </Navbar.Header>
                <Navbar.Collapse>
                  <Nav>
                    <Navbar.Brand eventKey={1}>
                      <NavLink to={`/personal`}><h4>MyRec</h4></NavLink>
                    </Navbar.Brand>
                    <Navbar.Brand eventKey={2}>
                      <NavLink to={`/`}><h4>Watch Later</h4></NavLink>
                    </Navbar.Brand>
                    <Navbar.Brand eventKey={3}>
                      <NavLink to={`/`}><h4>Group</h4></NavLink>
                    </Navbar.Brand>
                    <Navbar.Brand eventKey={4}>
                      <NavLink to={`/`}><h4>Movie News</h4></NavLink>
                    </Navbar.Brand>
                    <Navbar.Brand eventKey={4}>
                      <NavLink to={`/find_friends`}><h4>Find Friends</h4>
                      </NavLink>
                    </Navbar.Brand>
                  </Nav>
                  <Nav pullRight>
                    <Navbar.Brand eventKey={1}>
                      <NavLink to={this.state.user}><h4>Profile</h4></NavLink>
                    </Navbar.Brand>
                    <Navbar.Brand eventKey={2}>
                      <NavLink to={`/`}>
                        <button onClick={() => this.onClickLogout()}>
                          <h4>Sign Out</h4>
                        </button>
                      </NavLink>
                    </Navbar.Brand>
                  </Nav>
                </Navbar.Collapse>
              </Navbar>
    );
  }
}
export default MyNav;

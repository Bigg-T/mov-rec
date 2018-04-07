/**
 * Created by t on 2/10/18.
 */

import React, {Component} from 'react';
import {NavLink} from 'react-router-dom'
import {Navbar, NavItem, Nav} from 'react-bootstrap';
class MyNav extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isLoggedIn : false
    }
  }
  render() {
    console.log(this.state.isLoggedIn);
    if (this.state.isLoggedIn) {
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
              <Navbar.Brand eventKey={1} >
                <NavLink to={`/`}><h4>MyRec</h4></NavLink>
              </Navbar.Brand>
              <Navbar.Brand eventKey={2} >
                <NavLink to={`/`}><h4>Watch Later</h4></NavLink>
              </Navbar.Brand>
              <Navbar.Brand eventKey={3} >
                <NavLink to={`/`}><h4>Group</h4></NavLink>
              </Navbar.Brand>
              <Navbar.Brand eventKey={4} >
                <NavLink to={`/`}><h4>Movie News</h4></NavLink>
              </Navbar.Brand>
            </Nav>
            <Nav pullRight>
              <Navbar.Brand eventKey={1}>
                <NavLink to={`/`}><h4>Sign Out</h4></NavLink>
              </Navbar.Brand>
            </Nav>
          </Navbar.Collapse>
        </Navbar>
    );
  }
}
export default MyNav;

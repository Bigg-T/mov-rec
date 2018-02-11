/**
 * Created by t on 2/10/18.
 */

import React, {Component} from 'react';
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
              <a href="/">Movi3Hall</a>
            </Navbar.Brand>
            <Navbar.Toggle />
          </Navbar.Header>
          <Navbar.Collapse>
            <Nav pullRight>
              <NavItem eventKey={1} href="#">
                Sign In
              </NavItem>
              <NavItem eventKey={1} href="#">
                Register
              </NavItem>
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
              <a href="/">Movi3Hall</a>
            </Navbar.Brand>
            <Navbar.Toggle />
          </Navbar.Header>
          <Navbar.Collapse>
            <Nav>
              <NavItem eventKey={1} href="#">
                My Rec
              </NavItem>
              <NavItem eventKey={2} href="#">
                Watch Later
              </NavItem>
              <NavItem eventKey={3} href="#" >
                Group
              </NavItem>
              <NavItem eventKey={4} href="#">
                Movie News
              </NavItem>
            </Nav>
            <Nav pullRight>
              <NavItem eventKey={1} href="#">
                Sign Out
              </NavItem>
            </Nav>
          </Navbar.Collapse>
        </Navbar>
    );
  }
}
export default MyNav;
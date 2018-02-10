/**
 * Created by t on 2/10/18.
 */

import React, {Component} from 'react';


class MyNav extends Component {

  render() {
    return (
        <Navbar>
          <Navbar.Header>
            <Navbar.Brand>
              <a href="#home">React-Bootstrap</a>
            </Navbar.Brand>
          </Navbar.Header>
          <Nav>
            <NavItem eventKey={1} href="#">
              Link
            </NavItem>
            <NavItem eventKey={2} href="#">
              Link
            </NavItem>
          </Nav>
        </Navbar>
    );
  }
}

export default Navbar;
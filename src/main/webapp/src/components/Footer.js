//import React from 'react';
import React, {Component} from 'react';
import { Grid, Nav, NavItem } from 'react-bootstrap';
import {Container} from 'semantic-ui-react';
//import '../css/Footer.css';

class Footer extends Component {
render(){

  return (

  <Container className="container_footer">
    <footer className="footer">
      <Grid >
        <Nav justified >
          <NavItem
            eventKey={1}>
            Privacy policy
          </NavItem>
          <NavItem
            eventKey={2}
            title="Item">
            Terms & Conditions
          </NavItem>
          <NavItem
            eventKey={3}>
            Some other professional link
          </NavItem>
        </Nav>

        <div className="text-center small copyright">
          © TJASA 2018
        </div>
        <div className="footer navbar-fixed-bottom">
        </div>
      </Grid>
    </footer>
      </Container>
  );
}
}

export default Footer;

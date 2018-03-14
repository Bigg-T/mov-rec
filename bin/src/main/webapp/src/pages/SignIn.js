import React, { Component } from 'react';
import {Container, Form, Header } from 'semantic-ui-react';
import axios from 'axios';
import Footer from '../components/Footer';
import '../css/Footer.css';

class SignIn extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      submittedUserName: '',
      submittedPassword: ''};
      isLoggedin: false;
      errorMsg: "";
  }

  handleChange = (e, { name, value }) => this.setState({ [name]: value })

  handleSubmit() {
    const { username, password } = this.state;
    //should be doing post to backend
    // somehow this does not get updated in time before it git axios
    this.setState({ submittedUserName: username, submittedPassword: password });
    let URL = "http://localhost:8081/api/user/add_User/?";
    let fname = "fname=bigt2&";
    let lname = "lname=mofo&";
    let email = "email=bigt2@mofo.com&";
    let pw = "pw=mypass&username=bigtmofo2";
    let URL2 = "http://localhost:8081/api/user/validate_login/?";
    let un = "username=" + this.state.username;
    let pass = "pw=" + this.state.password;
    console.log("UserName:");
    console.log(this.state.username);
    axios.get(URL2+ un+'&'+pass)
    .then((code) => {
      console.log(code);
      this.setState({isLoggedin : code.data.isSuccess});
      // console.log("Status of account logged in:" + code.data.isSuccess);
      let isSucess = code.data.isSuccess;
      if (isSucess) {
        window.location.href = '/';// change the history path, to reroute (hack)
        window.localStorage['isLoggedIn'] = true;
      } else {
        this.setState({errorMsg : "You incorrect password or username."})
      }
    });
  }

  render() {
    const username = this.state.username;
    const password = this.state.password;
    const submittedUserName = this.state.submittedUserName;
    const submittedPassword = this.state.submittedPassword;

    return (
        <Container>
          <div>{this.state.errorMsg}</div>
          <Form onSubmit={() => this.handleSubmit()}>
            <Header as='h2'>Sign In</Header>
            <Form.Group widths="3">
              <Form.Input fluid required
                          label="User Name" placeholder='username' name='username'
                          value={this.state.username} onChange={this.handleChange}/>

            </Form.Group>
            <Form.Group widths="3">
              <Form.Input fluid required
                          label="Password" placeholder='password' name='password'
                          value={this.state.password} onChange={this.handleChange} />
            </Form.Group>
            <Form.Button>Submit</Form.Button>


          </Form>
          {/*<div>{Object.keys(this.state).map(key => key + " : " + this.state[key] + "\n")}</div>*/}
          {/*<p>{this.state.isLoggedin}</p>*/}
          <Footer title={Footer}/>
        </Container>
    );
  }
}

export default SignIn;

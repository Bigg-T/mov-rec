import React, { Component } from 'react';
import {Container, Form } from 'semantic-ui-react';
import axios from 'axios';
import {NavLink} from 'react-router-dom';
import Footer from '../components/Footer';
import '../css/Footer.css';

const options = [
  { key: 'm', text: 'Male', value: 'male' },
  { key: 'f', text: 'Female', value: 'female' },
];

class SignUp extends Component {
  constructor(props) {
    super(props);
    this.state = {
      firstName: '',
      lastName: '',
      username: '',
      password:'',
      email: '',
      gender: '',
      submittedName: '',
      submittedEmail: '',
      errorMsg: ''};
  }

  handleChange = (e, { name, value }) => this.setState({ [name]: value });

  handleSubmit() {
    const { name, email } = this.state;
    let URL = "http://localhost:8081/api/user/add_User/?";
    let URL2 = "http://moviehall.us-east-2.elasticbeanstalk.com"
              + "/api/user/add_User/?";
    let fname = "fname=" + this.state.firstName + "&";
    let lname = "lname=" + this.state.lastName + "&";
    let emailForm = "email=" + this.state.email + "&";
    let pw = "pw=" + this.state.password + "&";
    let username = "username=" + this.state.username;

    //should be doing post to backend
    this.setState({ submittedName: name, submittedEmail: email });
    axios.get(URL+fname+lname+emailForm+pw+username)
    .then((code) => {
      console.log(code);
      this.setState({isLoggedin : code.data.isSuccess});
      // console.log("Status of account logged in:" + code.data.isSuccess);
      let isSucess = code.data.isSuccess;
      if (isSucess) {
          window.localStorage['isLoggedIn'] = true;
          window.localStorage['user_id'] = code.data.user_id;
          window.location.href = '/';// change the history path, to reroute (hack)

      } else {
        this.setState({errorMsg : "User with the same email is already created"})
      }
    });
  }

  render() {
    const name = this.state.firstName;
    const email = this.state.email;
    const username = this.state.username;
    const password = this.state.password;
    const submittedName = this.state.submittedName;
    const submittedEmail = this.state.submittedEmail;

    const gender = this.state.gender;

    return (
        <Container>
          <div>{this.state.errorMsg}. <NavLink to="/signin">Sign in</NavLink></div>
          <Form onSubmit={() => this.handleSubmit()}>
            <Form.Group widths="equal">
              <Form.Field>
                <Form.Input fluid required
                            label="First Name" placeholder='First Name' name='firstName'
                            value={this.state.firstName} onChange={this.handleChange} />
              </Form.Field>
              <Form.Field>
                <Form.Input fluid required
                            label="Last Name" placeholder='Last Name' name='lastName'
                            value={this.state.lastName} onChange={this.handleChange} />
              </Form.Field>
            </Form.Group>
            <Form.Group widths="equal">
              <Form.Input fluid required
                          label="User Name" placeholder='username' name='username'
                          value={this.state.username} onChange={this.handleChange} />
              <Form.Input fluid required
                          label="Password" placeholder='password' name='password'
                          value={this.state.password} onChange={this.handleChange} />
            </Form.Group>
            <Form.Group widths="equal">
              <Form.Select fluid label='Birth Date' placeholder='Date'
                           value ="" onChange={this.handleChange}/>
              <Form.Select fluid label='Gender' options={options} placeholder='Gender'
                           onChange={this.handleChange}/>
            </Form.Group >
            <Form.Group widths="equal">
              <Form.Input fluid required
                          label="Email" placeholder='Email' name='email'
                          value={this.state.email} onChange={this.handleChange}/>
            </Form.Group>
            <Form.Checkbox label='I agree to the Terms and Conditions' />
            <Form.Button>Submit</Form.Button>

          </Form>
          {/*<div>{Object.keys(this.state).map(key => key + " : " + this.state[key] + "\n")}</div>*/}
          <Footer title={Footer}/>
        </Container>

    );
  }
}

export default SignUp;

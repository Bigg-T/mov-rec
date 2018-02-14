import React, { Component } from 'react';
import {Container, Form, Header } from 'semantic-ui-react';



class SignIn extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      submittedUserName: '',
      submittedPassword: ''};
  }

  handleChange = (e, { name, value }) => this.setState({ [name]: value })

  handleSubmit() {
    const { username, password } = this.state;

    //should be doing post to backend
    this.setState({ submittedUserName: username, submittedPassword: password })
  }

  render() {


    const username = this.state.username;
    const password = this.state.password;
    const submittedUserName = this.state.submittedUserName;
    const submittedPassword = this.state.submittedPassword;



    return (
        <Container>

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
        </Container>
    );
  }
}

export default SignIn;

import React, { Component } from 'react';
import {Container, Form } from 'semantic-ui-react';

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
      submittedEmail: '' };
  }

  handleChange = (e, { name, value }) => this.setState({ [name]: value })

  handleSubmit() {
    const { name, email } = this.state;

    //should be doing post to backend
    this.setState({ submittedName: name, submittedEmail: email })
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
              <Form.Input fluid label="Email" placeholder='Email' name='email'
                          value={this.state.email} onChange={this.handleChange}/>
            </Form.Group>
            <Form.Checkbox label='I agree to the Terms and Conditions' />
            <Form.Button>Submit</Form.Button>
          </Form>
          {/*<div>{Object.keys(this.state).map(key => key + " : " + this.state[key] + "\n")}</div>*/}
        </Container>
    );
  }
}

export default SignUp;
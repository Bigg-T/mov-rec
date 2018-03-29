import React, { Component } from 'react';
import {Container, Form } from 'semantic-ui-react';
import axios from 'axios';
import {NavLink} from 'react-router-dom';
import Footer from '../components/Footer';
import '../css/Footer.css';


class EditProfile extends Component {
  constructor(props) {
    super(props);
    this.state = {
      firstName: '',
      lastName: '',
      about_me: ''};
  }

  handleChange = (e, { name, value }) => this.setState({ [name]: value });

//Handles submission of profile data  
  handleSubmit() {
	 let user_request = window.localStorage.getItem("user_id");

	  if (user_request != null) {
		    //const { name, email } = this.state;
		    let URL = "http://localhost:8081/api/user/profile/edit/?";
		    let fname = "first_name=" + this.state.firstName + "&";
		    let lname = "last_name=" + this.state.lastName + "&";
		    let about_me = "about_me=" + this.state.about_me;
		    let userId = "user_request=" + user_request + "&"

		    //should be doing post to backend
		    //this.setState({ submittedName: name, submittedEmail: email });
		    axios.post(URL+userId+fname+lname+about_me)
		    .then((response) => {
		      console.log(response);
		      // console.log("Status of account logged in:" + code.data.isSuccess);
		      let isSucess = response.data.isSuccess;
		      if (isSucess) {
					window.alert("Successfully Updated Profile");
		      } else {
		    	  		window.alert(response.data.message);
		      }
		    });
	  }

  }
  
  
  componentWillMount() {
// 	 let profileId = this.props.params.id;
// 	 let user_request = window.localStorage.getItem("user_id");
 	let user_request = window.localStorage.getItem("user_id");
 	if (user_request != null) {
 	//let profileId = this.props.match.params.id;
 	//console.log("profileId: " + profileId);
 	//console.log("user_request: " + user_request);
 	axios.get("http://localhost:8081/api/user/profile/edit/?user_request=" + user_request)
     .then((code) => {
     	console.log("--------------------");
       console.log(code);
       let isSuccess = code.data.isSuccess;
       if (isSuccess) {
     	  	this.setState({firstName : code.data.first_name, lastName : code.data.last_name,
     	  		 about_me: code.data.about_me})
       }
     });
 }
 }

  render() {
	    const fname_ = this.state.firstName;
	    const lname_ = this.state.email;
	    const about_me_ = this.state.username;
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
              <Form.Input fluid
                          label="About Me" placeholder='About me' name='about_me'
                          value={this.state.about_me} onChange={this.handleChange} />
            </Form.Group>
            <Form.Group widths="equal">
            </Form.Group>
            <Form.Button>Save</Form.Button>

          </Form>
          {/*<div>{Object.keys(this.state).map(key => key + " : " + this.state[key] + "\n")}</div>*/}
          <Footer title={Footer}/>
        </Container>

    );
  }
}

export default EditProfile;

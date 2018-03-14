  /**
  * Created by Amit on 2/13/18
  */

import React, {Component} from 'react';
import {Item, Image, Grid, Rating, Container} from 'semantic-ui-react';
import {Badge, Glyphicon, Label} from 'react-bootstrap';
import { View, Text, AppRegistry } from 'react';
import {genImageURL5} from '../MovieDBConstant';
import axios from 'axios';
import {genMovieVid} from '../MovieDBConstant';
import YouTube from './YouTube';
  //import {View} from 'react-native';


  class Profile extends Component {
    constructor(props) {
      super(props);
//      this.state = {
//        youtube : [''],
//      }
    }
    componentWillMount() {
//      let movieID = this.props.location.state.referrer.id;//
//      axios.get(genMovieVid(movieID))
//      .then(res => {
//        if (res.data.results.length != 0) {
//          let data = res.data.results[0];
//          console.log('KEYYYYYYY');
//          console.log(data);
//          this.setState({youtube : [data.key]});
//        }
//
//      });

    }
    render() {
      //let YOUTUBE_BASE = 'https://www.youtube.com/watch?v=';
      //console.log("referrer")
//      console.log(this.props.location.state.referrer);
//      let name = this.state.youtube.map(a => a);
      //let YOUTUBE_VID = YOUTUBE_BASE+name[0];
      return (


<Container>

        <Grid >
        <Grid.Row streched>
           <Grid.Column width={14}>
           <h3>
           Jean Paul Torre
           </h3>
           </Grid.Column>
           <Grid.Column width={5}>
           <span> Add Friend </span>
{/*           <span> Remove Friend </span> */}
           </Grid.Column>
       </Grid.Row>

       
       
        <Grid.Row>
        <Grid.Column width={4}>
        <Image src={"http://www.personalbrandingblog.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png"} />
                 </Grid.Column>
        
        <Grid.Column width={10}>
        <Image src={"http://www.personalbrandingblog.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png"} />
                 </Grid.Column>
        </Grid.Row>

            <Grid.Row>
              <Grid.Column width={6}>
              <h3>About Me:</h3>
              </Grid.Column>
            </Grid.Row>

              <Grid.Row>
              <Grid.Column width={3}>
              <p>"I love movies, which is why I decided to join this website. I would like to meet people to watch movies with"</p>
              </Grid.Column>
            </Grid.Row>

            <Grid.Row>
              <Grid.Column width={15}>
                <span>
                </span>
              </Grid.Column>
            </Grid.Row>

            <Grid.Row>
              <Grid.Column width={15}>
                <span>
                Director:  Francis Lawrence
                </span>
              </Grid.Column>
            </Grid.Row>

            <Grid.Row>
              <Grid.Column width={15}>
                <span>
                Writers:  Peter Craig (screenplay), Danny Strong (screenplay)
                </span>
              </Grid.Column>
              </Grid.Row>
            <Grid.Row>
              <Grid.Column width={11}>
                <span>
                Stars: Jennifer Lawrence, Josh Hutcherson, Liam Hemsworth
                </span>
              </Grid.Column>

              <Grid.Column width={5}>
              <span>
              Reccomended By: JP, JP, JP
              </span>

              </Grid.Column>
            </Grid.Row>

          </Grid>
          </Container>
         );

    }
  }

  export default Profile;

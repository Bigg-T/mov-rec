  /**
  * Created by Amit on 2/13/18
  */

  import React, {Component} from 'react';
  import {Item, Image, Grid, Rating, Container} from 'semantic-ui-react';
  import {Badge, Glyphicon, Label} from 'react-bootstrap';
  import { View, Text, AppRegistry } from 'react';
  import {genImageURL5} from '../MovieDBConstant';
  import Footer from '../components/Footer';
  import '../css/Footer.css';
  //import {View} from 'react-native';


  class MovieDescription extends Component{

    render() {
      console.log("referrer")
      console.log(this.props.location.state.referrer);
      return (


<Container>

        <Grid >
        <Grid.Row streched>
           <Grid.Column width={14}>
           <h3>
           {/*The Hunger Games: Mockingjay, Part 1*/}
             {this.props.location.state.referrer.title}
           </h3>
           </Grid.Column>
           <Grid.Column width={2}>
           <Rating icon="star" maxStar={5} />
           <h8> Rate Movie </h8>
           </Grid.Column>
       </Grid.Row>

        <Grid.Row>
          <Grid.Column width={1}>
               <Badge>{this.props.location.state.referrer.vote_average}/10 <Glyphicon glyph="star" /></Badge>
          </Grid.Column>
          <Grid.Column width={1}>
               <Label>120 Min </Label>
          </Grid.Column>
          <Grid.Column width={2}>
               <Label> {this.props.location.state.referrer.genre_ids} </Label>
          </Grid.Column>
          <Grid.Column width={2}>
               <Label> {this.props.location.state.referrer.release_date}</Label>
          </Grid.Column>

        </Grid.Row>

            <Grid.Row>
              <Grid.Column width={6}>
                <Image src={genImageURL5(this.props.location.state.referrer.poster_path)} />


              </Grid.Column>
             <Grid.Column width={8} className="centered">
               <Image width={1000} height={200} src='https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAWxAAAAJGFiMTBjY2M5LTI4ZTAtNDNlOC04NTczLTI3ZmI1OThhMTAyZQ.jpg' />
               </Grid.Column>
            </Grid.Row>

            <Grid.Row>
              <Grid.Column width={15}>
                <span>
                  {this.props.location.state.referrer.overview}
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
          <Footer title={Footer}/>
          </Grid>
          </Container>


         );
    }
  }

  export default MovieDescription;

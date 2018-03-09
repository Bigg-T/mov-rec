  /**
  * Created by Amit on 2/13/18
  */

  import React, {Component} from 'react';
  import {Item, Image, Grid, Rating, Container} from 'semantic-ui-react';
  import {Badge, Glyphicon, Label} from 'react-bootstrap';
  import { View, Text, AppRegistry } from 'react';
  //import {View} from 'react-native';


  class MovieDescription extends Component{

    render() {
      return (


<Container>

        <Grid >
        <Grid.Row streched>
           <Grid.Column width={14}>
           <h3>
           The Hunger Games: Mockingjay, Part 1
           </h3>
           </Grid.Column>
           <Grid.Column width={2}>
           <Rating icon="star" maxStar={5} />
           <h8> Rate Movie </h8>
           </Grid.Column>
       </Grid.Row>

        <Grid.Row>
          <Grid.Column width={1}>
               <Badge>4/10 {this.props.rate} <Glyphicon glyph="star" /></Badge>
          </Grid.Column>
          <Grid.Column width={1}>
               <Label>120 Min </Label>
          </Grid.Column>
          <Grid.Column width={2}>
               <Label> Action/Science Fiction </Label>
          </Grid.Column>
          <Grid.Column width={2}>
               <Label> 11-20-2014 </Label>
          </Grid.Column>

        </Grid.Row>

            <Grid.Row>
              <Grid.Column width={3}>
                <Image src='https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAWxAAAAJGFiMTBjY2M5LTI4ZTAtNDNlOC04NTczLTI3ZmI1OThhMTAyZQ.jpg' />


              </Grid.Column>
             <Grid.Column width={8} className="centered">
               <Image width={1000} height={200} src='https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAWxAAAAJGFiMTBjY2M5LTI4ZTAtNDNlOC04NTczLTI3ZmI1OThhMTAyZQ.jpg' />
               </Grid.Column>
            </Grid.Row>

            <Grid.Row>
              <Grid.Column width={15}>
                <span>
                Description: Katniss Everdeen is in District 13 after she shatters the games forever. Under the leadership of President Coin and the advice of her trusted friends, Katniss spreads her wings as she fights to save Peeta and a nation moved by her courage.
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

        /*
        <Item.Group>
        <Item>


        <Item.Content>

        <Item.Meta>
        <Grid columns="equal">
        <Grid.Column width={10}>
        <h3 className="title">
           The Hunger Games: Mockingjay, Part 1
           </h3>
           </Grid.Column>


          <Grid.Column width={10}>
            <span className="date">
              Released on 2016 {this.props.releaseDate}
            </span>
            <span className="rating">
              4 {this.props.releaseDate}
            </span>
            <span className="run_time">
              120 minutes {this.props.run_time}
            </span>
            <span className="categories">
               Action, Adventure, Sci-Fi {this.props.categories}
            </span>
            <Badge>4/10 {this.props.rate} <Glyphicon glyph="star" /></Badge>
            </Grid.Column>


        <Grid.Column>
          <Badge>4/10 {this.props.rate} <Glyphicon glyph="star" /></Badge>
        </Grid.Column>
        </Grid>
        </Item.Meta>


        <Item.Image size='medium' src='https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAWxAAAAJGFiMTBjY2M5LTI4ZTAtNDNlOC04NTczLTI3ZmI1OThhMTAyZQ.jpg' />

        <Item.Description>
        <p>{description}</p>
        <p>
        This is the 3rd movie in the Hunger Games series.
        </p>
        </Item.Description>
        </Item.Content>
        </Item>

        <Item>
        <Item.Image size='small' src='https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAWxAAAAJGFiMTBjY2M5LTI4ZTAtNDNlOC04NTczLTI3ZmI1OThhMTAyZQ.jpg' />
        <Item.Content header='Jennifer Lawrence' description={description} />
        </Item>
        </Item.Group>
      );
      */

    }
  }

  export default MovieDescription;

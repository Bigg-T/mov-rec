/**
 * Created by t on 2/11/18.
 */
import React, {Component} from 'react';
import {Grid, Header, Container, Card} from 'semantic-ui-react';
import '../css/MovieRecPage.css';
import MovieSection from '../components/MovieSection';
<<<<<<< HEAD
import MySearch from '../components/MySearch';
import axios from 'axios';
import {genPopularList} from '../MovieDBConstant';

class MovieRecPage extends Component {

  constructor(props) {
    super(props);
    this.state = {
      myData : [],
    }
=======

class MovieRecPage extends Component {

  render() {
    return (
        <div>
          <div className="mysection">
            <Container>
              <MovieSection sectionName={"What's Hot"} perRow={2} perCol={3}/>
            </Container>
            <Container>
              <MovieSection sectionName={"Action"} perRow={2} perCol={3} />
            </Container>
            <Container>
              <MovieSection sectionName={"Comedy"} perRow={2} perCol={3} />
            </Container>
          </div>
        </div>
    );
>>>>>>> master
  }

<<<<<<< HEAD
  componentDidMount() {
    const UM = "https://api.themoviedb.org/3/movie/201/lists?api_key=492a79d4999e65c2324dc924891cb137&language=en-US&page=1";
    const UM2 = 'https://api.themoviedb.org/3/movie/popular?api_key=492a79d4999e65c2324dc924891cb137&language=en-US&page=1';
    const MDB_API_KEY = '492a79d4999e65c2324dc924891cb137';
    const BASE_MDB_URL = 'https://api.themoviedb.org/3/';
    let URL = BASE_MDB_URL+'movie/popular?api_key='+MDB_API_KEY + '&language=en-US&page=1';

    axios.get(genPopularList())
    .then(res => {
      const data = res.data;
      console.log('MyData');
      console.log(data.results);
      this.setState({myData : data.results });
    })
  }

  render() {
    let temp = [];
    this.state.myData.map(obj => temp.push(obj));
    console.log("sfdffdsdsfdfds  fsaffdsfdas");
    console.log(temp[1]);
    return (
        <div>
          <Container > <MySearch className='text-right'/></Container>
          <div className="mysection">
            <Container>
              <MovieSection myData ={temp} sectionName={"What's Hot"} perRow={2} perCol={3}/>
            </Container>
            <Container>
              <MovieSection myData ={temp} sectionName={"Action"} perRow={2} perCol={3} />
            </Container>
            <Container>
              <MovieSection myData ={temp} sectionName={"Comedy"} perRow={2} perCol={3} />
            </Container>
          </div>
        </div>
    );
  }
}
=======

>>>>>>> master



export default MovieRecPage;
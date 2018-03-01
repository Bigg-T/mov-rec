import React, { Component } from 'react';
import logo from './icons/logo.svg';
import './css/App.css';
import {Navbar} from 'react-bootstrap';
import MovieCard from './components/MovieCard';
import MyNav from './components/MyNav';
import MovieRecPage from './pages/MovieRecPage';
import MyRoute from './components/MyRoute';

class App extends Component {
  render() {
    return (
        <div>
          <MyNav value={"YOOOOO!"}/>
          <MyRoute/>
          {/*<MovieRecPage/>*/}
        </div>
    );
  }
}

export default App;

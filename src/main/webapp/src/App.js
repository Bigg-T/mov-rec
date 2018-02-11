import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import {Navbar} from 'react-bootstrap';
import MovieCard from './MovieCard'
import MyNav from './MyNav'

class App extends Component {
  render() {
    return (
        <div>
          <MyNav value={"YOOOOO!"}/>
        </div>
    );
  }
}

export default App;

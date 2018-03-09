import React, { Component } from 'react';
import logo from './icons/logo.svg';
import './css/App.css';
import {Navbar} from 'react-bootstrap';
import MovieCard from './components/MovieCard';
import MyNav from './components/MyNav';
import MovieRecPage from './pages/MovieRecPage';
import MyRoute from './components/MyRoute';

import Enzyme from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import Footer from './components/Footer';
import './css/Footer.css';

Enzyme.configure({ adapter: new Adapter() });

class App extends Component {
  render() {
    return (
        <div>
          <MyNav value={"YOOOOO!"}/>


          <div>
          {/*<MovieRecPage/>*/}
          <MyRoute/>
          </div>



        
        </div>
    );
  }
}

export default App;

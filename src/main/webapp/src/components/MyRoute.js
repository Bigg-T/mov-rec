/**
 * Created by t on 2/14/18.
 */
import React, {Component} from 'react';
import {Route, Switch} from 'react-router-dom';
import MovieRecPage from '../pages/MovieRecPage';
import SignUp from '../pages/SignUp';
import SignIn from '../pages/SignIn';
class MyRoute extends Component {

  render() {
    return (
      <main>
          <Switch>
            <Route exact path='/' component={MovieRecPage}/>
            <Route path='/signup' component={SignUp}/>
            <Route path='/signin' component={SignIn}/>
          </Switch>
      </main>
    );
  }
}

export default MyRoute;
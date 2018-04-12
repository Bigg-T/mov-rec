/**
 * Created by t on 2/14/18.
 */
import React, {Component} from 'react';
import {Route, Switch} from 'react-router-dom';
import MovieRecPage from '../pages/MovieRecPage';
import SignUp from '../pages/SignUp';
import SignIn from '../pages/SignIn';
import EditProfile from '../pages/EditProfile'
import MovieDescription from './MovieDescription';
import Profile from './Profile'
import FindFriend from '../pages/FindUsers';
import Admin from '../admin/Admin';
class MyRoute extends Component {

  render() {
    return (
      <main>
          <Switch>
            <Route exact path='/' component={MovieRecPage}/>
            <Route path='/signup' component={SignUp}/>
            <Route path='/signin' component={SignIn}/>
            <Route path='/movie/:id' component={MovieDescription}/>
            <Route path='/profile/:id' component={Profile}/>
            <Route path='/profile_edit' component={EditProfile}/>
            <Route path='/find_friends' component={FindFriend}/>
            <Route path='/admin' component={Admin}/>
          </Switch>
      </main>
    );
  }
}

export default MyRoute;

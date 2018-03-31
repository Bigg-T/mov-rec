import 'jsdom-global/register'; //at the top of file , even  , before importing react
import React from 'react';
import { mount, shallow } from 'enzyme';
import ReactDOM from 'react-dom';
import App from '../APP';
import MyNav from '../components/MyNav';
import MyRoute from '../components/MyRoute';
import Footer from '../components/Footer';
import SignIn from '../pages/SIGNIN';
import SignUp from '../pages/SIGNUP';
import MovieRecPage from '../pages/MovieRecPage';
import MovieDescription from '../pages/MovieDescription';

import jsdom from 'jsdom'


describe('<App />', () => {

//SignIn tests
  it('renders 1 <SignIn /> component', () => {
    const component = shallow(<SignIn name="signin" />);
    console.log(component);
    expect(component).toHaveLength(1);
  });
  describe('it renders props correctly', () => {
    const component = shallow(<SignIn name="signin" />);
    console.log(component.instance().props);
    expect(component.instance().props.name).toBe('signin');
  });
  describe('it updates the submit on button click', () => {
    const component = mount(<SignIn name="signin" />);
    const button = component.find('button');
    button.simulate('click');
    console.log(component.state());
    expect(component.state().username).toEqual('');
    expect(component.state().password).toEqual('');
  });

    });

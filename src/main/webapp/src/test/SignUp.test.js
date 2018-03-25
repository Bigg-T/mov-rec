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

describe('<App />', () => {

  it('renders 1 <SignUp /> component', () => {
    const component = shallow(<SignUp name="signup" />);
    console.log(component);
    expect(component).toHaveLength(1);
  });
  describe('it renders props correctly', () => {
    const component = shallow(<SignUp name="signup" />);
    console.log(component.instance().props);
    expect(component.instance().props.name).toBe('signup');
  });
  });

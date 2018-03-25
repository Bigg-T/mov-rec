import React from 'react';
import { mount, shallow } from 'enzyme';
import ReactDOM from 'react-dom';
import App from '../APP';
import YouTube from '../components/YouTube';
import MyRoute from '../components/MyRoute';
import Footer from '../components/Footer';
import SignIn from '../pages/SIGNIN';
import SignUp from '../pages/SIGNUP';
import MovieRecPage from '../pages/MovieRecPage';
import MovieDescription from '../pages/MovieDescription';

describe('<App />', () => {

//MovieRecPage
it('renders 1 <YouTube /> component', () => {
  const component = shallow(<MovieRecPage name="youtube" />);
  console.log(component);
  expect(component).toHaveLength(1);
});
describe('it renders props correctly', () => {
  const component = shallow(<YouTube name="youtube" />);
  console.log(component.instance().props);
  expect(component.instance().props.name).toBe('youtube');
});

});

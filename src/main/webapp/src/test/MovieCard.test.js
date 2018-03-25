import React from 'react';
import { mount, shallow } from 'enzyme';
import ReactDOM from 'react-dom';
import App from '../APP';
import MyNav from '../components/MyNav';
import MyRoute from '../components/MyRoute';
import MovieCard from '../components/MovieCard';
import SignIn from '../pages/SIGNIN';
import SignUp from '../pages/SIGNUP';
import MovieRecPage from '../pages/MovieRecPage';
import MovieDescription from '../pages/MovieDescription';

describe('<App />', () => {

//MovieRecPage
it('renders 1 <MovieCard /> component', () => {
  const component = shallow(<MovieRecPage name="moviecard" />);
  console.log(component);
  expect(component).toHaveLength(1);
});
describe('it renders props correctly', () => {
  const component = shallow(<MovieCard name="moviecard" />);
  console.log(component.instance().props);
  expect(component.instance().props.name).toBe('moviecard');
});

});

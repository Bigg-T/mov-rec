import React from 'react';
import { mount, shallow } from 'enzyme';
import ReactDOM from 'react-dom';
import App from '../App';
import MovieCard from '../components/MovieCard';


describe('<App />', () => {

//MovieRecPage
it('renders 1 <MovieCard /> component', () => {
  const component = shallow(<MovieCard name="moviecard" />);
  console.log(component);
  expect(component).toHaveLength(1);
});
describe('it renders props correctly', () => {
  const component = shallow(<MovieCard name="moviecard" />);
  console.log(component.instance().props);
  expect(component.instance().props.name).toBe('moviecard');
});

});

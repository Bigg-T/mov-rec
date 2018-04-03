import React from 'react';
import { mount, shallow } from 'enzyme';
import ReactDOM from 'react-dom';
import App from '../App';
import MovieRecPage from '../pages/MovieRecPage';


describe('<App />', () => {

//MovieRecPage
it('renders 1 <MovieRecPage /> component', () => {
  const component = shallow(<MovieRecPage name="movierecpage" />);
  console.log(component);
  expect(component).toHaveLength(1);
});
describe('it renders props correctly', () => {
  const component = shallow(<MovieRecPage name="movierecpage" />);
  console.log(component.instance().props);
  expect(component.instance().props.name).toBe('movierecpage');
});

});

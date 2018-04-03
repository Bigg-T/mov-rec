import React from 'react';
import { mount, shallow } from 'enzyme';
import ReactDOM from 'react-dom';
import App from '../App';
import YouTube from '../components/YouTube';

describe('<App />', () => {

//MovieRecPage
it('renders 1 <YouTube /> component', () => {
  const component = shallow(<YouTube name="youtube" />);
  console.log(component);
  expect(component).toHaveLength(1);
});
describe('it renders props correctly', () => {
  const component = shallow(<YouTube name="youtube" />);
  console.log(component.instance().props);
  expect(component.instance().props.name).toBe('youtube');
});

});

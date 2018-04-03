import React from 'react';
import { mount, shallow } from 'enzyme';
import ReactDOM from 'react-dom';
import App from '../App';
import MyNav from '../components/MyNav';


describe('<App />', () => {

//MovieRecPage
it('renders 1 <MyNav /> component', () => {
  const component = shallow(<MyNav name="mynav" />);
  console.log(component);
  expect(component).toHaveLength(1);
});
describe('it renders props correctly', () => {
  const component = shallow(<MyNav name="mynav" />);
  console.log(component.instance().props);
  expect(component.instance().props.name).toBe('mynav');
});

});

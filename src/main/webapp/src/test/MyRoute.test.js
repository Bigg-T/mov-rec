import React from 'react';
import { mount, shallow } from 'enzyme';
import ReactDOM from 'react-dom';
import App from '../App';
import MyRoute from '../components/MyRoute';


describe('<App />', () => {

//MovieRecPage
it('renders 1 <MyRoute /> component', () => {
  const component = shallow(<MyRoute name="myroute" />);
  console.log(component);
  expect(component).toHaveLength(1);
});
describe('it renders props correctly', () => {
  const component = shallow(<MyRoute name="myroute" />);
  console.log(component.instance().props);
  expect(component.instance().props.name).toBe('myroute');
});

});

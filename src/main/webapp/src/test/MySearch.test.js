import React from 'react';
import { mount, shallow } from 'enzyme';
import ReactDOM from 'react-dom';
import App from '../App';
import MySearch from '../components/MySearch';



describe('<App />', () => {

//MovieRecPage
it('renders 1 <MySearch /> component', () => {
  const component = shallow(<MySearch name="mysearch" />);
  console.log(component);
  expect(component).toHaveLength(1);
});
describe('it renders props correctly', () => {
  const component = shallow(<MySearch name="mysearch" />);
  console.log(component.instance().props);
  expect(component.instance().props.name).toBe('mysearch');
});

});

import React from 'react';
import { mount, shallow } from 'enzyme';
import ReactDOM from 'react-dom';
import App from '../App';
import Footer from '../components/Footer';


describe('<App />', () => {

//MovieRecPage
it('renders 1 <Footer /> component', () => {
  const component = shallow(<Footer name="footer" />);
  console.log(component);
  expect(component).toHaveLength(1);
});
describe('it renders props correctly', () => {
  const component = shallow(<Footer name="footer" />);
  console.log(component.instance().props);
  expect(component.instance().props.name).toBe('footer');
});

});

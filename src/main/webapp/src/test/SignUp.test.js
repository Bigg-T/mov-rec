import React from 'react';
import { mount, shallow } from 'enzyme';
import ReactDOM from 'react-dom';
import App from '../App';
import SignUp from '../pages/SignUp';


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

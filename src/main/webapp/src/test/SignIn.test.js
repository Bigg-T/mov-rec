import React from 'react';
import {shallow} from 'enzyme';
import SignIn from '../pages/SignIn';
import Enzyme from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import { mount } from 'enzyme';
import ReactDOM from 'react-dom';

Enzyme.configure({ adapter: new Adapter() });

jest.unmock('../pages/SignIn');

//import React from 'react';
//import { shallow } from 'enzyme';

//import SignIn from '../SignIn';
import App from '../App';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<SignIn />, div);
  ReactDOM.unmountComponentAtNode(div);
});

describe('<SignIn />', () => {

  it('expects to change the status when clicking the button', () => {
    const wrapper = shallow(<SignIn />);

    expect(wrapper.state().username).toBe('');
    //expect(wrapper.contains(<p>Status is {'Off'}</p>)).toBe(true);

    //wrapper.find("submitButton").simulate('click');

    expect(wrapper.state().password).toBe('');
    //expect(wrapper.contains(<p>Status is {'On'}</p>)).toBe(true);

});




it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<SignIn />, div);
  ReactDOM.unmountComponentAtNode(div);
});




it('checks "handlesubmit()" on click', () => {
var expects = require('expect');
    const wrapper = mount(<SignIn />);
    wrapper.update();
    const spy = jest.spyOn(wrapper.instance(), 'handleSubmit');

    wrapper.find('#submitButton').simulate('click');
    //const spy = jest.spyOn(wrapper.instance(), 'handleSubmit');
    expects(spy).toHaveBeenCalled();
  });

});

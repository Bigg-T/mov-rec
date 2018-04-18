jest.unmock('../App');

import React from 'react';
import { shallow } from 'enzyme';

import App from '../App';
import MyNav from '../components/MyNav';
import MyRoute from '../components/MyRoute';
import Footer from '../components/Footer'

describe('<App />', () => {

  //testing for nav component
  it('renders a <MyNav> with a static text', () => {
    const wrapper = shallow(<App />);
    expect(wrapper.contains(
        <MyNav value={"YOOOOO!"}/>
    )).toBe(true);
  });

  //testing for footer component
  it('renders a <Footer> with a static text', () => {
    const wrapper = shallow(<App />);
    expect(wrapper.contains(
         <Footer title={Footer}/>
    )).toBe(false);
  });


});

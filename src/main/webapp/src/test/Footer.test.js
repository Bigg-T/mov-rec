
import React from 'react';
import { shallow } from 'enzyme';

import App from '../APP';
import MyNav from '../components/MyNav';
import MyRoute from '../components/MyRoute';
import Footer from '../components/Footer';

import Enzyme from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import '../css/Footer.css';



describe('<Footer />', () => {


  it('expects to change the status when clicking the button', () => {
    const wrapper = shallow(<Footer />);

    //expect(wrapper.contains(
      //  <Container className="container_footer"/>
    //)).toBe(true);

    });


});

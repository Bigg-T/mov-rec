//import React from 'react';
import React, { Component } from 'react';
import Enzyme from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

Enzyme.configure({ adapter: new Adapter() });

//const Foo = React.component({
class Foo extends Component {
  render() {
    return (
      <p>I am not a very smart component...</p>
    );
  }
}
export default Foo;

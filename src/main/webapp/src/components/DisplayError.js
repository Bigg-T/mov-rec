/**
 * Created by t on 4/10/18.
 */

/**
 * DisplayError component use to display error to user.
 */

import React, {Component} from 'react';
import {Message} from 'semantic-ui-react';

class DisplayError extends Component {
  render() {
    return (
        <Message negative>
          <Message.Header>{this.props.status}</Message.Header>
          <p>{this.props.msg}</p>
        </Message>
    );
  }
}

export default DisplayError;
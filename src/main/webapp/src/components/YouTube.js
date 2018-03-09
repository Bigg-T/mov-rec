/**
 * Created by t on 2/22/18.
 */

import React, {Component} from 'react';
class YouTube extends Component {

  render() {
    var videoSrc = "https://www.youtube.com/embed/" +
        this.props.video + "?autoplay=" +
        this.props.autoplay + "&rel=" +
        this.props.rel + "&modestbranding=" +
        this.props.modest;
    return (
        <div>
          <iframe width="100%" height="500px"
                  src={videoSrc}
                  frameborder="0"/>
        </div>
    );
  };
}

export default YouTube;
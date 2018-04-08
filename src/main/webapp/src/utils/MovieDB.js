/**
 * Created by t on 2/12/18.
 */
import _ from 'lodash';

export const filterGenre = (jsonList, genreId) => {
  return jsonList.filter(mov => {
    let isContain = false;
    for(let i = 0; i < mov.genres.length; i++) {
      console.log("movie IIIIDDD");
      if (mov.genres[i].id == genreId) {
        //console.log(mov.genre_ids[i]);
        isContain = true;
        break;
      }
    }
    return isContain;
  });
};

export const sortByAvgRate = (listMovie) => {
  return _.orderBy(listMovie, 'vote_average', 'desc');
};


class MovieDB {

  configSetting(url) {
    var settings = {
      "async": true,
      "crossDomain": true,
      "url": url,
      "method": "GET",
      "headers": {},
      "data": "{}"
    };
  }

  async fetchAsyc(url) {
    let data = await (await fetch(`https://httpbin.org/get`,{
      method: `GET`,
      headers: {
        'authorization': 'BaseAuth W1lcmxsa='
      }
    })).json();
    console.log(data);
  }
}

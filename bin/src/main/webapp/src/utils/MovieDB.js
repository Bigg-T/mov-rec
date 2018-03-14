/**
 * Created by t on 2/12/18.
 */

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
/**
 * Created by t on 4/12/18.
 */
import React, {Component} from 'react';
import {Button, Container} from 'semantic-ui-react';
import axios from 'axios';
import * as constant from '../config';

class Admin extends Component {
  constructor(props) {
    super(props);
    this.state = {

    };
  }

  startPrediction() {
    let api = 'api/movie/calculateRec/';
    let URL = constant.MOVI3HALL_BASE_API+api;
    // let ar = [3,4,5,6,7,8,9,10,11,12,13,14,15,16,17];
    // let mo = [189,49017,53182,54415,59981,68737,70706,72784,76649,76757,79316,82657,82702,82703,85350,86834,86835,87093,87492,91314,94590,97020,98566,99367,100042,100241,100402,102382,102651,105001,110390,110416,112454,113091,113833,114155,114750,114982,116149,118340,119450,119892,120467,122917,124470,124905,126172,127585,131631,134688,136797,136835,137094,137106,137113,137321,137876,138103,141043,144336,145220,147441,147778,149509,150689,151368,152760,152790,154400,155084,156022,156597,157099,157336,157350,157353,157424,157825,157829,157832,157845,157849,157851,161064,164251,166085,167073,168164,168530,169760,169917,170522,170687,171274,171372,171846,172385,173497,174372,177494,177572,179150,179538,180296,180299,180305,181533,184098,184315,184345,184346,185341,187017,187596,188161,188207,190469,190859,192102,192134,192149,193223,193610,193612,193726,193893,194188,194662,195589,196867,197696,197796,197950,198182,198184,198185,198663,199575,199925,199933,200505,200727,201086,201088,203739,203834,204765,204922,204968,205587,205596,205775,206192,206296,206487,206563,206821,207703,207933,208286,208763,208869,209244,209451,209840,210577,210860,212231,212606,212778,212843,214314,215032,215211,215379,216015,216282,217993,218043,218778,218836,220933,221667,221902,222649,222899,222935,223954,224141,224972,225044,225565,225574,225886,226448,226486,226857,227156,227159,227300,227306,227348,227719,227735,227783,228028,228150,228161,228165,228194,228203,228326,228445,228967,228968,228970,228973,229194,229297,229559,230179,230743,230779,231576,232572,232672,232679,233470,233487,234200,234212,235260,236399,236735,236737,236751,237791,237808,238215,238603,238636,239562,239563,239566,239571,239573,239678,240510,240832,240916,241239,241251,241254,241432,241554,241765,241771,241842,241848,241855,241868,241968,242022,242042,242076,242083,242088,242090,242095,242224,242310,242512,242582,242643,242661,242828,243531,243683,243684,243935,243938,243940,244063,244260,244264,244267,244268,244403,244458,244506,244509,244517,244534,244536,244562,244563,244566,244688,244761,244772,244786,244801,245158,245175,245700,245706,245775,245844,245859,245891,245916,246011,246080,246320,246400,246403,246741,246743,246860,247182,248211,248212,248231,248376,248390,248504,248507,248774,248888,248933,249164,249170,249266,249660,249688,249724,249772,249923,250066,250114,250124,250225,250538,250546,250551,250577,250578,250643,250650,250651,250657,250658,250660,250666,250668,250700,250705,250761,250766,250769,250777,250798,250833,251016,251232,251321,251519,251555,251736,251852,251981,251987,252102,252171,252178,252200,252360,252391,252457,252607,252680,252822,252838,252841,253046,253065,253235,253239,253251,253254,253256,253266,253270,253272,253284,253287,253290,253292,253295,253306,253307,253310,253312,253331,253377,253406,253454,253686,253849,253851,254065,254143,254188,254191,254193,254194,254200,254375,254435,254473,254474,254578,254721,254869,254904,255268,255343,255491,255756,255890,255913,256030,256092,256106,256108,256274,256306,256311,256347,256474,256503,256561,256591,256593,256628,256731,256740,256835,256876,256912,256917,257091,257093,257155,257346,257442,257444,257451,257648,257668,257974,258086,258096,258126,258193,258251,258358,258673,258750,258751,258805,258893,259018,259233,259395,259610,259943,259956,260001,260030,260063,260202,260339,260346,260535,261037,261038,261101,261375,261768,261770,261814,261820,261825,261855,262338,262340,262391,262481,262500,262543,262551,262840,262904,262958,263105,263111,263281,263510,263614,263855,264166,264337,264656,264767,265010,265016,265169,265177,265180,265189,265195,265208,265226,265228,265297,265449,265497,265712,265832,265929,266031,266034,266040,266044,266080,266082,266285,266396,266537,266723,266856,266880,267268,267355,267480,267481,267573,267793,267806,267863,267872,267999,268171,268174,268238,268272,268386,269100,269173,269576,269650,269711,269714,270005,270302,270303,270343,270403,270668,270938,270946,271185,271397,271674,271724,272543,272602,272691,272692,272693,273271,273334,273621,273632,273641,273895,273997,274805,275318,275619,276120,276122,276496,276690,276839,276843,276844,276901,276908,276918,276922,276935,277355,277368,277432,277547,277686,277688,277846,278086,278329,278677,278772,278878,279414,279661,279828,279914,279967,279972,280002,280218,280509,280795,281230,281298,282041,282268,282296,282297,282376,282813,283235,283330,283384,283726,284276,284293,284296,284306,284427,284536,284711,284785,285025,285026,285135,285176,285213,285245,285270,285423,285463,285597,285700,286532,286554,286654,286709,286805,286817,286875,286971,287084,287233,287299,287301,287422,287486,287495,287504,287509,287524,287590,288036,288130,288158,288281,288312,288313,288581,288708,288789,288916,289153,289183,289278,289314,289333,289390,289394,289416,289575,289727,289728,290542,290555,290695,290764,290802,290999,291155,291362,291869,292191,292396,292483,293205,293262,293271,293310,293444,293450,293461,293491,293771,293861,293880,294132,294308,294538,294652,294690,294959,295058,295087,295144,295315,295490,295538,295698,295887,295914,295958,296626,296778,297222,297265,297544,297556,297596,297608,298040,298077,298317,298445,298533,298538,298935,299049,299796,300364,300441,300487,300706,300803,301225,301231,301566,302118,302376,302429,302496,302828,303457,303542,303623,303742,304023,304336,304850,305091,305127,305276,305450,305932,306197,306650,306963,307113,307663,307946,308024,308027,308032,308160,308165,308361,308369,308453,308457,308571,308638,308639,308640,309049,309242,309245,309298,309299,309503,309581,309875,310001,310119,310121,310589,311301,312152,312497,312526,312813,314285,315044,315049,315057,315360,315362,315855,315882,316269,318040,318044,318224,319070,319074,319075,319078,319079,319091,320001,320003,320004,320316,321594,321644,322456,322518,323431,324260,324279,325205,326359,326536,326665,327029,327225,328233,328346,328595,329227,330275,358724];
    // let iter = Array.from(new Array(100),(val,index)=>index);
    // ar.forEach(userId => {
    //   iter.forEach(not => {
    //     let movieIndex2Rate = Math.floor(Math.random() * mo.length);
    //     let rate = Math.floor(Math.random() * 6);
    //     let movieId = mo[movieIndex2Rate];
    //     let addApi = 'api/movie/addUserRates/?movie_id='+movieId+'&user_id='+userId+'&rate='+rate;
    //     let URL = constant.MOVI3HALL_BASE_API+addApi;
    //     axios.get(URL);
    //   });
    // });
    axios.get(URL).then(res => {
      console.log(res.data);
      res.data;
    });
  }

  render() {
    let startPrediction = (
        <Button content='Start User Rating Predictions' primary onClick={() => this.startPrediction()} />
    );
    return (
      <div>
        <Container>
          {startPrediction}
        </Container>
      </div>
    );
  }
}

export default Admin;
/**
 * Created by t on 2/13/18.
 */

import React, {Component} from 'react';
import axios from 'axios';
import _ from 'lodash';
import { Search, Grid, Header } from 'semantic-ui-react';
import { Route, Redirect } from 'react-router-dom';

class MySearch extends Component {

  constructor(props) {
    super(props);
    this.state = {
      keyword : '',
      myData : [],
      isRedirect: false
    };
  }

  componentDidMount() {
    const UM = "https://api.themoviedb.org/3/movie/201/lists?api_key=492a79d4999e65c2324dc924891cb137&language=en-US&page=1";
    const UM2 = 'https://api.themoviedb.org/3/movie/popular?api_key=492a79d4999e65c2324dc924891cb137&language=en-US&page=1';
    const MDB_API_KEY = '492a79d4999e65c2324dc924891cb137';
    const BASE_MDB_URL = 'https://api.themoviedb.org/3/';
    let URL = BASE_MDB_URL+'/movie/popular?api_key='+MDB_API_KEY + '&language=en-US&page=1';

    axios.get(UM2)
    .then(res => {
      const data = res.data;
      console.log('MyData');
      console.log(data.results);
      this.setState({myData : data.results });
    })
  }

  handleClick() {
    const MDB_API_KEY = '492a79d4999e65c2324dc924891cb137';
    const MDB_IMG = 'https://image.tmdb.org/t/p/w500/432BowXw7a4fWXSONxBaFKqvW4f.jpg'; //w[image_width]
    const MDB_BASE_URL = 'https://api.themoviedb.org/3/';
    let URL =MDB_BASE_URL+ '/search/movie?api_key=492a79d4999e65c2324dc924891cb137&query=Transformer';

  }
  componentWillMount() {
    this.resetComponent()
  }

  resetComponent() {
    this.setState({ isLoading: false, results: [], value: '' });
  }

  handleResultSelect = (e, { result }) => {

    this.setState({ value: result.title });
    this.setState({ isRedirect: true })
  };


  handleSearchChange = (e, { value }) => {
    this.setState({ isLoading: true, value })

    setTimeout(() => {
      if (this.state.value.length < 1) return this.resetComponent();

      const re = new RegExp(_.escapeRegExp(this.state.value), 'i');
      const isMatch = result => re.test(result.title);

      this.setState({
        isLoading: false,
        results: _.filter(this.state.myData, isMatch),
      })
    }, 500)
  };

  render() {
    const { isLoading, value, results } = this.state;

    return (
        <div>
          <div>
            <Route exact path="/" render={() => (
                this.state.isRedirect ? (
                        <Redirect to={{
                          pathname: '/movie/:name',
                          search: '?keyword='+this.state.value,
                          state: { referrer: this.state.myData }
                        }}/>
                    ) : (
                        ""
                    )
            )}/>
          </div>
        <Grid>
          <Grid.Column width={8}>
            <Search
                loading={isLoading}
                onResultSelect={this.handleResultSelect}
                onSearchChange={this.handleSearchChange}
                results={results}
                value={value}
                {...this.props}
            />
          </Grid.Column>
        </Grid>
        </div>
    )
  }
}

export default MySearch;
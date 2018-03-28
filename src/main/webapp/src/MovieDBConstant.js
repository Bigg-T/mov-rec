/**
 * Created by t on 2/12/18.
 */

export const MDB_IMG_BASE_URL5 = 'https://image.tmdb.org/t/p/w500/';
export const MDB_IMG_BASE_URL3 = 'https://image.tmdb.org/t/p/w300/';
export const MDB_BASE_URL = 'https://api.themoviedb.org/3/';
export const MDB_API_KEY = '492a79d4999e65c2324dc924891cb137';
export const MDB_GENRE_IDS = [{"id":28,"name":"Action"},{"id":12,"name":"Adventure"},{"id":16,"name":"Animation"},{"id":35,"name":"Comedy"},{"id":80,"name":"Crime"},{"id":99,"name":"Documentary"},{"id":18,"name":"Drama"},{"id":10751,"name":"Family"},{"id":14,"name":"Fantasy"},{"id":36,"name":"History"},{"id":27,"name":"Horror"},{"id":10402,"name":"Music"},{"id":9648,"name":"Mystery"},{"id":10749,"name":"Romance"},{"id":878,"name":"Science Fiction"},{"id":10770,"name":"TV Movie"},{"id":53,"name":"Thriller"},{"id":10752,"name":"War"},{"id":37,"name":"Western"}]

export const genImageURL5 = (poster_path) => {
  return MDB_IMG_BASE_URL5 + poster_path;
};

export const genImageURL3 = (poster_path) => {
  return MDB_IMG_BASE_URL3 + poster_path;
};

export const genPopularList = () => {
  return ('http://moviehall.us-east-2.elasticbeanstalk.com/api/movie/popular/?num=20');//(MDB_BASE_URL + 'movie/popular?api_key='+ MDB_API_KEY +'&language=en-US&page=1');
};

export const genGenreID = (id) => {
  return '';
};

export const genPopular = (num) => {
  return '';
};

export const genMovieVid = (movieID) => {
  //https://api.themoviedb.org/3/movie/211672/videos?api_key=492a79d4999e65c2324dc924891cb137
  return MDB_BASE_URL + 'movie/'+ movieID +'/videos?api_key=' + MDB_API_KEY;
};

## USER RATES

### Add Ratings

```$xslt
GET /api/movie/addUserRates/
Example: /api/movie/addUserRates/?movie_id=3&user_id=17&rate=5
```
#### If success:
```json
{
 "userRatingId":134,
 "isSuccess":true,"status":"OK"
}
```
#### Error case:
```json
{
 "message":"User Not Logged In",
 "isSuccess":false,
 "status":"BAD_REQUEST"
}
```
---------
### Get RATING FROM A USER
```$xslt
GET /api/movie/getUserRates/
```
```json
{ 
 "isSuccess":true,
 "status":"OK",
 "topMovies":[[1,3],[3,5]]
}
```
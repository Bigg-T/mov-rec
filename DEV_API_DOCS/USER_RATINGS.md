## USER RATES

### Add Ratings

```$xslt
GET /api/movie/addUserRates/   
Example: /api/movie/addUserRates/?movie_id=3&user_id=17&rate=5
```
<table>
  <tbody>
    <tr>
      <th>Params</th>
      <th>Type</th>
      <th>IsRequire</th>
    </tr>
    <tr>
      <td align="center">movie_id</td>
      <td align="center">Int</td>
      <td align="center">Y</td>
    </tr>
    <tr>
      <td align="center">user_id</td>
      <td align="center">Int</td>
      <td align="center">Y</td>
    </tr>
    <tr>
      <td align="center">rate</td>
      <td align="center">Int</td>
      <td align="center">Y</td>
    </tr>
  </tbody>
</table>


##### If success:
```json
{
 "userRatingId":134,
 "isSuccess":true,"status":"OK"
}
```
##### Error case:
```json
{
 "message":"User Not Logged In",
 "isSuccess":false,
 "status":"BAD_REQUEST"
}
```
---------
##### Get RATING FROM A USER
```$xslt
GET /api/movie/getRecommendedMovies/
Example: /api/movie/getRecommendedMovies/?user_id=14
```
<table>
  <tbody>
    <tr>
      <th>Params</th>
      <th>Type</th>
      <th>IsRequire</th>
    </tr>
    <tr>
      <td align="center">user_id</td>
      <td align="center">Int</td>
      <td align="center">Y</td>
    </tr>
  </tbody>
</table>

```json
{ 
 "isSuccess":true,
 "status":"OK",
 "topMovies":[[1,3],[3,5]]
}
```
----

#### Update all user rating predictions
```$xslt
GET /api/movie/calculateRec/
No params needed
```
<table>
   <tbody>
     <tr>
       <th>Params</th>
       <th>Type</th>
       <th>IsRequire</th>
     </tr>
     <tr>
       <td align="center">N/A</td>
       <td align="center">N/A</td>
       <td align="center">N/A</td>
     </tr>
   </tbody>
</table>

```json
{
  "movies": "...<List of movie ids>...",
  "rates": "...<2D Arrays of Double>...",
  "user": "..<List of id>..",
  "isSuccess": true
}
```
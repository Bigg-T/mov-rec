## Top Movies

### Add Top Movies

```$xslt
GET /api/movie/addTopMovie/
Example: /api/movie/addTopMovie/?movie_id=189&user_id=17&rank=1&description="asdf"
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
      <td align="center">rank</td>
      <td align="center">Int</td>
      <td align="center">Y</td>
    </tr>
    <tr>
      <td align="center">description</td>
      <td align="center">String</td>
      <td align="center">Y</td>
  </tbody>
</table>


##### If success:
```json
{
  "isSuccess":true,
  "status":"OK"
}
```
---------

## Top Movies

### Get Top Movies

```$xslt
GET /api/movie/getTopMovies/
Example: /api/movie/getTopMovies/?user_id=17&limit=3
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
    <tr>
      <td align="center">limit</td>
      <td align="center">Int</td>
      <td align="center">Y</td>
    </tr>
</table>


##### If success:
```json
{
  "movies":
  	[
  		{
  		  "id":993,
  		  "movie_id":189,
  		  "user_id":17,
  		  "description":"\"asdf\"",
  		  "rank":1
  		}
  	]
  	,"isSuccess":true,
  	"status":"OK"
}
  		  
  
```
---------



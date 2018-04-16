## Movie Friend Recommendation

#### User recommends friend a movie
```$xslt
GET /api/user/recommend/
Example: /api/user/recommend/?userId=14&friendId=15&movieId=3

Success JSON RESULT:
 {"recId":1141,"isSuccess":true,"status":"OK"}
```
--------

#### Gets all the movies recommended to a user by their friends
```$xslt
GET /api/user/recommend/getAll/
Example: /api/user/recommend/getAll/?userId=14

Success:
JSON RESULT: {"tmdb_ids":[234545452,234545455],"isSuccess":true,"status":"OK"}
```

--------


#### Deletes a movie recommended to the given user by a friend
```$xslt
GET /api/user/recommend/delete/
Example: /api/user/recommend/delete/?userId=14&movieId=3

Success 
JSON RESULT: {"isSuccess":true,"status":"OK"}
```

--------

#### Gets all the friends you can recommend a given movie to
##### Needed so that a user does not recommend the same movie to the same user
```$xslt
GET /api/user/recommend/get_friends/
Example: /api/user/recommend/get_friends/?userId=14&movieId=3

Success JSON RESULT: 
{"friends":[{"name": Jean Paul, "id": 423}, {"name": Thien, "id": 4343}],"isSuccess":true,"status":"OK"}

```

--------
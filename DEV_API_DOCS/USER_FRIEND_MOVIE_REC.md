## Movie Friend Recommendation

#### User recommends friend a movie
```$xslt
POST /api/user/recommend/
Example: /api/user/recommend/?userId=14&friendId=15&movieId=3
```

--------

#### Gets all the movies recommended to a user by their friends
```$xslt
GET /api/user/recommend/getAll/
Example: /api/user/recommend/getAll/?userId=14
```

--------


#### Deletes a movie recommended to the given user by a friend
```$xslt
POST /api/user/recommend/delete/
Example: /api/user/recommend/delete/?userId=14&movieId=3
```

--------

#### Gets all the friends you can recommend a given movie to
##### Needed so that a user does not recommend the same movie to the same user
```$xslt
GET /api/user/recommend/get_friends/
Example: /api/user/recommend/get_friends/?userId=14&movieId=3
```

--------
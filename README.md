# saltsidetest
==============
- (1) Download/git clone the project.
- (2) Need to have maven install and working.
- (3) port 27017 should not be in use.
- (4) run mvn test to run test cases(from the project directory).
- (5) run mvn install to application(from the project directory). 

# POST
- Post Request : Return status code 201/created, if the bird was added successfully
```
{"name":"Peacok","family":"Animalia","continents":["africa","europe","asia"],"visible":true}
```
- Sample Post Response
```
{"id":"593bb56a2789340f5c492ddf","name":"Peacok","family":"Animalia","continents":["africa","europe",”asia”],"added":"2017-06-10","visible":true}
```

- Post Request : Return status code 400/Bad request if the any mandatory fields were missing or if input was invalid
- When “name” field is missing
```
{"family":"Animalia","continents":["africa","europe","asia"],"visible":true}
```
- Post Response
```
{"status":400,"code":4001,"message":"Name field missing"}
```

- When “family” field is missing
```
{"name":"Peacok","continents":["africa","europe","asia"],"visible":true}
```
- Post Response
```
{"status":400,"code":4001,"message":"Family field missing"}
```
- When “continents” field is missing
```
{"name":"Peacok","family":"Animalia","visible":true}
```
- Post Response
```
{"status":400,"code":4001,"message":"Continents field missing"}
```

- When “visible” field is missing
```
{"name":"Peacok","family":"Animalia","continents":["africa","europe","asia"]}
```
- Post Response
```
{"id":"593bb8ed2789340f5c492de1","name":"Peacok","family":"Animalia","continents":["africa","europe","asia"],"added":"2017-06-10","visible":false}
```

- When Bad malformed json
```
{"name":"Peacok","family":"Animalia","continents":"africa","visible":true}
```
- Post Response
```
{"status":400,"code":4002,"message":"Bad Request"}
```

# GET
- Open http://localhost:8080/birds - List all birds.
```
http://localhost:8080/birds
```
- Get Response
```
 ["593bb3d22789340f5c492dde","593bb56a2789340f5c492ddf"]
```


# GET/(id)
Open http://localhost:8080/birds/{id} - Get details on a specific bird(for simplicationfaction introduced birdId, which is added by user while creation).
```
http://localhost:8080/birds/593bb3d22789340f5c492dde
```
- Get Response
```
{"id":"593bb3d22789340f5c492dde","name":"fifth","family":"firstly","continents":["africa","europe"],"added":"2017-06-10","visible":true}
```
- available but set as not visible bird request.
```
http://localhost:8080/birds/593bbb902789340f5c492de2
```
- Get Response
```
Status 200 OK
```

- No available bird request.
```
http://localhost:8080/birds/123
```
- Get Response
```
Status 404
```
# DELETE
- Delete http://localhost:8080/birds/{id}
- available Delete bird/id request with empty body .
```
http://localhost:8080/birds/593bb3d22789340f5c492dde
```
- Get Response
```
Status 200 OK
```

- Not available Delete bird/id request with empty body .
```
http://localhost:8080/birds/123
```
- Get Response
```
Status 404 Not Found
```
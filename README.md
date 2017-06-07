# saltsidetest
==============
- (1) Download the project.
- (2) Need to have maven install and working.
- (3) port 27017 should not be in use.
- (4) run mvn test to run test cases(from the project directory).
- (5) run mvn install to application(from the project directory). 

- Open http://localhost:8080/birds - List all birds.
==================================================
- Open http://localhost:8080/birds{birdid} - Get details on a specific bird(for simplicationfaction introduced birdId, which is added by user while creation).


- Sample Post Request (Please provide unique bird id)
```
{

"title": "POST /birds [request]",
"description": "Add a new bird to the library",
"type": "object",
"required": [
"name",
"family",
"continents"
],
"birdId":1,
"additionalProperties": false,
"properties": {
"name": {
"type": "string",
"description": "English bird name"
},
"family": {
"type": "string",
"description": "Latin bird family name"
},
"continents": {
"type": "array",
"description": "Continents the bird exist on",
"minItems": 1,
"items": {
"type": "string"
},
"uniqueItems": true
},
"added": {
"type": "string",
"description": "Date the bird was added to the registry. Format YYYY-MM-DD"
},
"visible": {
"type": "boolean",
"description": "Determines if the bird should be visible in lists"
}
}
}
```
- Sample Response
```
{
    "id": "593883122789340462ab5fde",
    "title": "POST /birds [request]",
    "description": "Add a new bird to the library",
    "type": "object",
    "required": [
        "name",
        "family",
        "continents"
    ],
    "additionalProperties": false,
    "properties": {
        "name": {
            "type": "string",
            "description": "English bird name"
        },
        "family": {
            "type": "string",
            "description": "Latin bird family name"
        },
        "continents": {
            "type": "array",
            "description": "Continents the bird exist on",
            "minItems": 1,
            "items": {
                "type": "string"
            },
            "uniqueItems": true
        },
        "added": {
            "type": "string",
            "description": "Date the bird was added to the registry.2017-06-07T22:49:54.381Z"
        },
        "visible": {
            "type": "boolean",
            "description": "Determines if the bird should be visible in lists"
        }
    },
    "birdId": 1,
    "schema": null
}
```
 - Delete

http://localhost:8080/birds/{birdid}

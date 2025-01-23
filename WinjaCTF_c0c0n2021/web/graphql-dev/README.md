## Graphql Challenge

## Run the below commands to get Started after cloning the repo
```
docker-compose up
```
Server will start listening on port 4445.

## Solution

On visiting the home page(http://localhost:4445), there is a hint for the technologies that can be used for fetching the data like the rest, graphql, soap, etc.

This project is using spring with graphql.
Playground library and actuator endpoint are being used for development which is not disabled in prod.

1. Visit the /playground and then the user will get the login schema to query into the database.
2. Login Query accepts two params(username and password), which the user has to retrieve from the actuator endpoint (/actuator/info)
3. A flag is stored in the desc part of the schema

```
query{
  login(username:"hoyChANcEN",password:"UleTHEMBAtuLzAr")
  {
    username
    password
    Desc
    role
  }
}

```

### Ref:
- https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html
- https://www.graphql-java.com/

## Flag
Flag{3StvZstqDjLH15ZAaeSa_graphql_is_good}
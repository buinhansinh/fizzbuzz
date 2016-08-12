## Depedencies

* Vertx
* jax-rs, spock for testing
* Heroku platform

## Heroku Public link

```
https://stormy-cliffs-15199.herokuapp.com/fizzbuzz/classic
```

## To test api

```
curl -k -H "Content-type: application/json" --data '{"numbers":"1,2,3,4,5,6,7,8,9,10"}' https://stormy-cliffs-15199.herokuapp.com/fizzbuzz/classic
```

## To build project

```
mvn clean install
```

## To deploy to Heroku

```
mvn clean install -pl restapi-vertx heroku:deploy
```



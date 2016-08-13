## Depedencies

* Vertx
* jax-rs, spock for testing
* Heroku platform
* 
## Performance Test Report using BlazeMeter

```
https://a.blazemeter.com/app/?public-token=gEwKrXlk9Ca5Z3DfhQ7fwjPv8RdZOVWkLcPhhkwFfHimeZBilD#masters/15491331/summary
```

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



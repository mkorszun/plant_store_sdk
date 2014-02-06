[![Build Status](https://travis-ci.org/mkorszun/plant_store_sdk.png?branch=master)](https://travis-ci.org/mkorszun/plant_store_sdk)
[![Coverage Status](https://coveralls.io/repos/mkorszun/plant_store_sdk/badge.png)](https://coveralls.io/r/mkorszun/plant_store_sdk)
# Plant Store Java SDK

## Installation:

### Clone repository

~~~bash
$ git clone git@github.com:mkorszun/plant_store_sdk.git
$ cd plant_store_sdk
~~~

### Deploy to local maven repo

~~~bash
$ mvn deploy
~~~

### Use as a dependency

~~~xml
<dependency>
    <groupId>plant_store_sdk</groupId>
    <artifactId>plant_store_sdk</artifactId>
    <version>0.0.2</version>
</dependency>
~~~

## Example usage:

~~~java
// Get token (authorize with service username / password)
Token token = new TokenEndpoint().getToken("EMAIL", "PASS");
System.out.println(token.getToken());

PlantEndpoint plantEndpoint = new PlantEndpoint();

// List all user plants
ArrayList<Plant> plants = plantEndpoint.list(token.getToken());
for (Plant p : plants) {
    System.out.println(p.getDescription());
}

// Details of specific plant
Plant plant = plantEndpoint.read(token.getToken(), 2);
System.out.println(plant.getName());

// Create plant
NewPlantRequest request = new NewPlantRequest("stokrotka", "blabla", 8);
plantEndpoint.create(token.getToken(), request);

// List all kinds
ArrayList<Kind> kinds = new KindEndpoint().list(token.getToken());
for (Kind k : kinds) {
    System.out.println(k.getName());
}
~~~
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
    <version>0.0.1</version>
</dependency>
~~~

## Example usage:

~~~java
// Get token (authorize with service username / password)
Token token = new TokenEndpoint().getToken("mkorszun@gmail.com", "dupadupa");
System.out.println(token.getToken());

// List all user plants
ArrayList<Plant> plants = new PlantEndpoint().list(token.getToken());
for (Plant p : plants) {
    System.out.println(p.getDescription());
}

// Details of specific plant
Plant plant = new PlantEndpoint().read(token.getToken(), 2);
System.out.println(plant.getName());
~~~
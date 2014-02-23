package client.endpoint;

import client.ClientTest;
import client.http.HTTPClient;
import client.http.exception.HTTPClientException;
import client.request.NewPlantRequest;
import client.request.NewPlantRequestFixture;
import com.github.restdriver.clientdriver.ClientDriverRequest;
import model.Plant;
import model.PlantFixture;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static model.builders.KindBuilder.buildKind;

public class PlantEndpointTest extends ClientTest {

    @Test
    public void readPlantEmpty() throws IOException, HTTPClientException {
        setupDriver("/api/123/plant/1", "null", 200, "application/json");
        Plant plant = new PlantEndpoint(new HTTPClient(driver.getBaseUrl())).read("123", 1);
        Assert.assertEquals(null, plant);
    }

    @Test
    public void readPlant() throws IOException, HTTPClientException {
        JSONObject body = new PlantFixture().withId(1).withDescription("blabla").withName("rose").withKind(buildKind()).build();
        setupDriver("/api/123/plant/1", body.toJSONString(), 200, "application/json");
        Plant p = new PlantEndpoint(new HTTPClient(driver.getBaseUrl())).read("123", 1);
        Assert.assertEquals(1, p.getId());
        Assert.assertEquals("rose", p.getName());
        Assert.assertEquals("blabla", p.getDescription());
    }

    @Test
    public void createPlant() throws IOException, HTTPClientException {
        String expectedBody = new NewPlantRequestFixture().withName("name").withDescription("desc").withKindId(1232131).build();
        setupDriver(ClientDriverRequest.Method.POST, "/api/123/plant", expectedBody, 201, "application/json");
        NewPlantRequest request = new NewPlantRequest("name", "desc", 1232131);
        new PlantEndpoint(new HTTPClient(driver.getBaseUrl())).create("123", request);
    }

    @Test
    public void listPlantsEmpty() throws IOException, HTTPClientException {
        setupDriver("/api/123/plant", "[]", 200, "application/json");
        List<Plant> plants = new PlantEndpoint(new HTTPClient(driver.getBaseUrl())).list("123");
        Assert.assertEquals(0, plants.size());
    }

    @Test
    public void listPlants() throws IOException, HTTPClientException {

        JSONObject p1 = new PlantFixture().withId(1).withDescription("blabla").withName("rose").withKind(buildKind()).build();
        JSONObject p2 = new PlantFixture().withId(2).withDescription("blabla2").withName("rose2").withKind(buildKind()).build();

        setupDriver("/api/123/plant", JSONArray.toJSONString(asList(p1, p2)), 200, "application/json");
        List<Plant> plants = new PlantEndpoint(new HTTPClient(driver.getBaseUrl())).list("123");
        Assert.assertEquals(1, plants.get(0).getId());
        Assert.assertEquals("rose", plants.get(0).getName());
        Assert.assertEquals("blabla", plants.get(0).getDescription());
        Assert.assertEquals(2, plants.get(1).getId());
        Assert.assertEquals("rose2", plants.get(1).getName());
        Assert.assertEquals("blabla2", plants.get(1).getDescription());
    }
}

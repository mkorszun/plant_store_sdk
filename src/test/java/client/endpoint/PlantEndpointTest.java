package client.endpoint;

import client.ClientTest;
import client.http.HTTPClient;
import client.http.exception.HTTPClientException;
import client.request.NewPlantRequest;
import com.github.restdriver.clientdriver.ClientDriverRequest;
import model.ErrorFixture;
import model.Plant;
import model.PlantFixture;
import model.builders.KindBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

public class PlantEndpointTest extends ClientTest {

    @Test
    public void testListPlantsError() throws IOException, HTTPClientException {
        exception.expect(HTTPClientException.class);
        exception.expectMessage("unauthorized");
        String body = new ErrorFixture().withReason("unauthorized").build();
        setupDriver("/api/123/plant", body, 401, "application/json");
        new PlantEndpoint(new HTTPClient(driver.getBaseUrl())).list("123");
    }

    @Test
    public void testReadPlant() throws IOException, HTTPClientException {

        JSONObject body = new PlantFixture().withId(1).withDescription("blabla")
                .withName("rose").withKind(KindBuilder.buildKind()).build();

        setupDriver("/api/123/plant/1", body.toJSONString(), 200, "application/json");
        Plant p = new PlantEndpoint(new HTTPClient(driver.getBaseUrl())).read("123", 1);
        Assert.assertEquals(1, p.getId());
        Assert.assertEquals("rose", p.getName());
        Assert.assertEquals("blabla", p.getDescription());
    }

    @Test
    public void testListPlants() throws IOException, HTTPClientException {

        JSONObject p1 = new PlantFixture().withId(1).withDescription("blabla")
                .withName("rose").withKind(KindBuilder.buildKind()).build();
        JSONObject p2 = new PlantFixture().withId(2).withDescription("blabla2")
                .withName("rose2").withKind(KindBuilder.buildKind()).build();

        setupDriver("/api/123/plant", JSONArray.toJSONString(asList(p1, p2)), 200, "application/json");
        List<Plant> plants = new PlantEndpoint(new HTTPClient(driver.getBaseUrl())).list("123");
        Assert.assertEquals(1, plants.get(0).getId());
        Assert.assertEquals("rose", plants.get(0).getName());
        Assert.assertEquals("blabla", plants.get(0).getDescription());
        Assert.assertEquals(2, plants.get(1).getId());
        Assert.assertEquals("rose2", plants.get(1).getName());
        Assert.assertEquals("blabla2", plants.get(1).getDescription());
    }

    @Test
    public void testCreatePlant() throws IOException, HTTPClientException {
        setupDriver(ClientDriverRequest.Method.POST, "/api/123/plant", 201, "application/json");
        NewPlantRequest request = new NewPlantRequest("name", "desc", 1232131);
        new PlantEndpoint(new HTTPClient(driver.getBaseUrl())).create("123", request);
    }
}

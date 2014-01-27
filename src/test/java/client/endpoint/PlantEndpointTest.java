package client.endpoint;

import client.ClientTest;
import client.http.HTTPClient;
import client.http.exception.HTTPClientException;
import model.ErrorFixture;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class PlantEndpointTest extends ClientTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testListPlantsError() throws IOException, HTTPClientException {
        exception.expect(HTTPClientException.class);
        exception.expectMessage("unauthorized");
        String body = new ErrorFixture().withReason("unauthorized").build();
        setupDriver("/api/123/plant", body, 401, "application/json");
        new PlantEndpoint(new HTTPClient(driver.getBaseUrl())).list("123");
    }
}

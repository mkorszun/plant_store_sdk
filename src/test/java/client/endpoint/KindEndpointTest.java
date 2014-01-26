package client.endpoint;

import client.http.HTTPClient;
import client.http.exception.HTTPClientException;
import model.ErrorFixture;
import model.Kind;
import model.KindsListBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.ArrayList;

public class KindEndpointTest extends EndpointTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testListKindsJsonError() throws IOException, HTTPClientException {
        exception.expect(HTTPClientException.class);
        exception.expectMessage("invalid_token");
        String body = new ErrorFixture().withReason("invalid_token").build();
        setupDriver("/api/123456/kind", body, 401, "application/json");
        new KindEndpoint(new HTTPClient(driver.getBaseUrl())).list("123456");
    }

    @Test
    public void testListKindsPlainError() throws IOException, HTTPClientException {
        exception.expect(HTTPClientException.class);
        exception.expectMessage("Unauthorized");
        setupDriver("/api/123456/kind", "", 401, "text/plain");
        new KindEndpoint(new HTTPClient(driver.getBaseUrl())).list("123456");
    }

    @Test
    public void testListKindsOK() throws IOException, HTTPClientException {

        setupDriver("/api/123456/kind", KindsListBuilder.build(), 200, "application/json");
        KindEndpoint client = new KindEndpoint(new HTTPClient(driver.getBaseUrl()));

        ArrayList<Kind> kinds = client.list("123456");
        Assert.assertEquals(1, kinds.size());
        Assert.assertEquals(1, kinds.get(0).getId());
        Assert.assertEquals("Gerbera", kinds.get(0).getName());
        Assert.assertEquals("Gerbera jamesonii", kinds.get(0).getLatinName());
        Assert.assertEquals("moderate", kinds.get(0).getTreatment().getWatering());
        Assert.assertEquals("indirect", kinds.get(0).getTreatment().getInsolation());
        Assert.assertEquals(14, kinds.get(0).getTreatment().getTemperatureMin());
        Assert.assertEquals(21, kinds.get(0).getTreatment().getTemperatureMax());
        Assert.assertEquals("low", kinds.get(0).getTreatment().getHumidity());
        Assert.assertEquals(null, kinds.get(0).getTreatment().getComment());
    }
}

package client.endpoint;

import client.ClientTest;
import client.http.HTTPClient;
import client.http.exception.HTTPClientException;
import model.ErrorFixture;
import model.Kind;
import model.builders.KindBuilder;
import org.json.simple.JSONArray;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static java.util.Arrays.asList;

public class KindEndpointTest extends ClientTest {

    @Test
    public void testListKindsError() throws IOException, HTTPClientException {
        exception.expect(HTTPClientException.class);
        exception.expectMessage("invalid_token");
        String body = new ErrorFixture().withReason("invalid_token").build();
        setupDriver("/api/123456/kind", body, 401, "application/json");
        new KindEndpoint(new HTTPClient(driver.getBaseUrl())).list("123456");
    }

    @Test
    public void testListKindsOK() throws IOException, HTTPClientException {

        String body = JSONArray.toJSONString(asList(KindBuilder.buildKind()));
        setupDriver("/api/123456/kind", body, 200, "application/json");
        KindEndpoint client = new KindEndpoint(new HTTPClient(driver.getBaseUrl()));

        ArrayList<Kind> kinds = client.list("123456");
        Assert.assertEquals(1, kinds.size());
        Assert.assertEquals(1, kinds.get(0).getId());
        Assert.assertEquals("Gerbera", kinds.get(0).getName());
        Assert.assertEquals("Gerbera jamesonii", kinds.get(0).getLatinName());
        Assert.assertEquals("moderate", kinds.get(0).getTreatment().getWateringSeason());
        Assert.assertEquals("plentiful", kinds.get(0).getTreatment().getWateringRest());
        Assert.assertEquals(true, kinds.get(0).getTreatment().isDryBetweenWateringInSeason());
        Assert.assertEquals(false, kinds.get(0).getTreatment().isDryBetweenWateringInRest());
        Assert.assertEquals("indirect", kinds.get(0).getTreatment().getInsolation());
        Assert.assertEquals(14, kinds.get(0).getTreatment().getSeasonTempMin());
        Assert.assertEquals(21, kinds.get(0).getTreatment().getSeasonTempMax());
        Assert.assertEquals(10, kinds.get(0).getTreatment().getRestTempMin());
        Assert.assertEquals(16, kinds.get(0).getTreatment().getRestTempMax());
        Assert.assertEquals("low", kinds.get(0).getTreatment().getHumidity());
        Assert.assertEquals(null, kinds.get(0).getTreatment().getComment());
    }
}

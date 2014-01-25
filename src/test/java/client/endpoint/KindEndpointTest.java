package client.endpoint;

import client.http.HTTPClient;
import client.http.exception.HTTPClientException;
import com.github.restdriver.clientdriver.ClientDriverRequest;
import com.github.restdriver.clientdriver.ClientDriverRule;
import model.Kind;
import model.KindFixture;
import model.TreatmentFixture;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.github.restdriver.clientdriver.RestClientDriver.giveResponse;
import static com.github.restdriver.clientdriver.RestClientDriver.onRequestTo;

public class KindEndpointTest {

    @Rule
    public ClientDriverRule driver = new ClientDriverRule();

    @Test
    public void testListKindsOK() throws IOException, HTTPClientException {

        JSONObject treatment = new TreatmentFixture()
                .withWatering("moderate")
                .withInsolation("indirect")
                .withTemperatureMin(14)
                .withTemperatureMax(21)
                .withHumidity("low")
                .withComment(null)
                .build();

        JSONObject kind = new KindFixture()
                .withId(1)
                .withName("Gerbera")
                .withLatinName("Gerbera jamesonii")
                .withTreatment(treatment)
                .build();

        JSONArray resp = new JSONArray();
        resp.add(kind);

        driver.addExpectation(
                onRequestTo("/api/123456/kind")
                        .withMethod(ClientDriverRequest.Method.GET),
                giveResponse(resp.toJSONString())
                        .withStatus(200));

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

package client.endpoint;

import com.github.restdriver.clientdriver.ClientDriverRequest;
import com.github.restdriver.clientdriver.ClientDriverRule;
import org.junit.Rule;

import java.util.Map;

import static com.github.restdriver.clientdriver.RestClientDriver.giveResponse;
import static com.github.restdriver.clientdriver.RestClientDriver.onRequestTo;

public class EndpointTest {

    @Rule
    public ClientDriverRule driver = new ClientDriverRule();

    public void setupDriver(String url, String body, int code, String contentType) {
        driver.addExpectation(
                onRequestTo(url).withMethod(ClientDriverRequest.Method.GET),
                giveResponse(body).withStatus(code).withContentType(contentType)
        );
    }

    public void setupDriver(String url, String body, int code, String contentType, Map<String, Object> params) {
        driver.addExpectation(
                onRequestTo(url).withParams(params).withMethod(ClientDriverRequest.Method.GET),
                giveResponse(body).withStatus(code).withContentType(contentType)
        );
    }
}

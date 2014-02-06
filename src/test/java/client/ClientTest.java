package client;

import com.github.restdriver.clientdriver.ClientDriverRequest;
import com.github.restdriver.clientdriver.ClientDriverRule;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.Map;

import static com.github.restdriver.clientdriver.RestClientDriver.*;

public class ClientTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

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

    public void setupDriver(ClientDriverRequest.Method method, String url, int code, String contentType) {
        driver.addExpectation(
                onRequestTo(url).withMethod(method),
                giveEmptyResponse().withStatus(code).withContentType(contentType)
        );
    }
}

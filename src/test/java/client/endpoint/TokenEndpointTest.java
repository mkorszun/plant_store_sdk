package client.endpoint;

import client.http.HTTPClient;
import client.http.exception.HTTPClientException;
import client.utils.QueryParamsBuilder;
import model.ErrorFixture;
import model.Token;
import model.TokenFixture;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class TokenEndpointTest extends EndpointTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetTokenJsonError() throws IOException, HTTPClientException {
        exception.expect(HTTPClientException.class);
        exception.expectMessage("unauthorized");
        String body = new ErrorFixture().withReason("unauthorized").build();
        QueryParamsBuilder builder = new QueryParamsBuilder();
        builder.set("email", "email").set("password", "pass");
        setupDriver("/api/token", body, 401, "application/json", builder.build());
        new TokenEndpoint(new HTTPClient(driver.getBaseUrl())).getToken("email", "pass");
    }

    @Test
    public void testGetTokenPlainError() throws IOException, HTTPClientException {
        exception.expect(HTTPClientException.class);
        exception.expectMessage("Unauthorized");
        QueryParamsBuilder builder = new QueryParamsBuilder();
        builder.set("email", "email").set("password", "pass");
        setupDriver("/api/token", "", 401, "text/plain", builder.build());
        new TokenEndpoint(new HTTPClient(driver.getBaseUrl())).getToken("email", "pass");
    }

    @Test
    public void testGetTokenOK() throws IOException, HTTPClientException {
        QueryParamsBuilder builder = new QueryParamsBuilder();
        builder.set("email", "email").set("password", "pass");
        String tokenStr = new TokenFixture().withToken("token1234").build();
        setupDriver("/api/token", tokenStr, 200, "application/json", builder.build());
        Token token = new TokenEndpoint(new HTTPClient(driver.getBaseUrl())).getToken("email", "pass");
        Assert.assertEquals("token1234", token.getToken());
    }
}

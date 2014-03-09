package client.endpoint;

import client.ClientTest;
import client.fixtures.ErrorFixture;
import client.fixtures.TokenFixture;
import client.model.Token;
import com.okrest.http.HTTPClient;
import com.okrest.http.HTTPException;
import com.okrest.utils.QueryParamsBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TokenEndpointTest extends ClientTest {

    @Test
    public void testGetTokenError() throws IOException, HTTPException {
        exception.expect(HTTPException.class);
        exception.expectMessage("unauthorized");
        String body = new ErrorFixture().withReason("unauthorized").build();
        QueryParamsBuilder builder = new QueryParamsBuilder();
        builder.set("email", "email").set("password", "pass");
        setupDriver("/api/token", body, 401, "application/json", builder.build());
        new TokenEndpoint(new HTTPClient(driver.getBaseUrl())).getToken("email", "pass");
    }

    @Test
    public void testGetTokenOK() throws IOException, HTTPException {
        QueryParamsBuilder builder = new QueryParamsBuilder();
        builder.set("email", "email").set("password", "pass");
        String tokenStr = new TokenFixture().withToken("token1234").build();
        setupDriver("/api/token", tokenStr, 200, "application/json", builder.build());
        Token token = new TokenEndpoint(new HTTPClient(driver.getBaseUrl())).getToken("email", "pass");
        Assert.assertEquals("token1234", token.getToken());
    }
}

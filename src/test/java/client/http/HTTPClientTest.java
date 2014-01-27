package client.http;

import client.ClientTest;
import client.http.exception.HTTPClientException;
import model.ErrorFixture;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class HTTPClientTest extends ClientTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testOK() throws IOException, HTTPClientException {
        setupDriver("/path", "body", 200, "text/plain");
        HTTPClient httpClient = new HTTPClient(driver.getBaseUrl());
        String resp = new String(httpClient.request("GET", "/path"));
        Assert.assertEquals("body", resp);
    }

    @Test
    public void testJsonError() throws IOException, HTTPClientException {
        exception.expect(HTTPClientException.class);
        exception.expectMessage("unauthorized");
        String body = new ErrorFixture().withReason("unauthorized").build();
        setupDriver("/path", body, 401, "application/json");
        new HTTPClient(driver.getBaseUrl()).request("GET", "/path");
    }

    @Test
    public void testPlainError() throws IOException, HTTPClientException {
        exception.expect(HTTPClientException.class);
        exception.expectMessage("Unauthorized");
        setupDriver("/path", "", 401, "text/plain");
        new HTTPClient(driver.getBaseUrl()).request("GET", "path");
    }
}

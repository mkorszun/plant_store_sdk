package client.endpoint;

import client.http.HTTPClient;
import client.http.exception.HTTPClientException;
import client.utils.QueryParamsBuilder;
import model.Token;

import java.io.IOException;

public class TokenEndpoint extends Endpoint {

    public TokenEndpoint() {

    }

    public TokenEndpoint(HTTPClient client) {
        super(client);
    }

    public Token getToken(String email, String password) throws IOException, HTTPClientException {
        QueryParamsBuilder builder = new QueryParamsBuilder();
        builder.set("email", email).set("password", password);
        byte[] body = httpClient.request("GET", "api/token", builder.build());
        return mapper.readValue(body, Token.class);
    }
}

package client.endpoint;

import client.model.Token;
import com.okrest.http.HTTPClient;
import com.okrest.http.HTTPException;
import com.okrest.http.HTTPMethod;
import com.okrest.utils.QueryParamsBuilder;

import java.io.IOException;

public class TokenEndpoint extends Endpoint {

    public TokenEndpoint() {

    }

    public TokenEndpoint(HTTPClient client) {
        super(client);
    }

    public Token getToken(String email, String password) throws IOException, HTTPException {
        QueryParamsBuilder builder = new QueryParamsBuilder();
        builder.set("email", email).set("password", password);
        byte[] body = httpClient.request(HTTPMethod.GET, "api/token", builder.build());
        return mapper.readValue(body, Token.class);
    }
}

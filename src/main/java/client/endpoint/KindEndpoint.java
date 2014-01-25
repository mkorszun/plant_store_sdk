package client.endpoint;

import client.http.HTTPClient;
import client.http.exception.HTTPClientException;
import com.google.common.base.Joiner;
import model.Kind;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;

public class KindEndpoint extends Endpoint {

    public KindEndpoint() {

    }

    public KindEndpoint(HTTPClient client) {
        super(client);
    }

    public ArrayList<Kind> list(String token) throws IOException, HTTPClientException {
        byte[] body = httpClient.request("GET", Joiner.on("/").join("api", token, "kind").toString());
        return mapper.<ArrayList>readValue(body, new TypeReference<ArrayList<Kind>>() {
        });
    }
}

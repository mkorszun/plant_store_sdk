package client.endpoint;

import client.model.Kind;
import com.google.common.base.Joiner;
import com.okrest.http.HTTPClient;
import com.okrest.http.HTTPException;
import com.okrest.http.HTTPMethod;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;

public class KindEndpoint extends Endpoint {

    public KindEndpoint() {

    }

    public KindEndpoint(HTTPClient client) {
        super(client);
    }

    public ArrayList<Kind> list(String token) throws IOException, HTTPException {
        byte[] body = httpClient.request(HTTPMethod.GET, Joiner.on("/").join("api", token, "kind").toString());
        return mapper.<ArrayList>readValue(body, new TypeReference<ArrayList<Kind>>() {
        });
    }
}

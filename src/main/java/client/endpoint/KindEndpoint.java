package client.endpoint;

import client.http.exception.HTTPClientException;
import client.utils.QueryParamsBuilder;
import model.Kind;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;

public class KindEndpoint extends Endpoint {

    public ArrayList<Kind> list(String token) throws IOException, HTTPClientException {
        QueryParamsBuilder builder = new QueryParamsBuilder();
        byte[] body = httpClient.request("GET", "api/" + token + "/kind", builder.build());
        return mapper.<ArrayList>readValue(body, new TypeReference<ArrayList<Kind>>() {
        });
    }
}

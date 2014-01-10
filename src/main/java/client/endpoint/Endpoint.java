package client.endpoint;

import client.http.HTTPClient;
import org.codehaus.jackson.map.ObjectMapper;

public class Endpoint {

    protected HTTPClient httpClient;
    protected ObjectMapper mapper;

    protected Endpoint() {
        this.httpClient = new HTTPClient();
        this.mapper = new ObjectMapper();
    }
}

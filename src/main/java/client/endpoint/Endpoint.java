package client.endpoint;

import com.okrest.http.HTTPClient;
import org.codehaus.jackson.map.ObjectMapper;

public class Endpoint {

    protected HTTPClient httpClient;
    protected ObjectMapper mapper;

    protected Endpoint() {
        this.httpClient = new HTTPClient();
        this.mapper = new ObjectMapper();
    }

    protected Endpoint(HTTPClient client) {
        this.httpClient = client;
        this.mapper = new ObjectMapper();
    }
}

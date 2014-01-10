package client.endpoint;

import client.http.exception.HTTPClientException;
import client.utils.QueryParamsBuilder;
import model.Plant;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;

public class PlantEndpoint extends Endpoint {

    public ArrayList<Plant> list(String token) throws IOException, HTTPClientException {
        QueryParamsBuilder builder = new QueryParamsBuilder();
        byte[] body = httpClient.request("api/" + token + "/plant", builder.build());
        return mapper.<ArrayList>readValue(body, new TypeReference<ArrayList<Plant>>() {
        });
    }

    public Plant read(String token, long id) throws IOException, HTTPClientException {
        QueryParamsBuilder builder = new QueryParamsBuilder();
        byte[] body = httpClient.request("api/" + token + "/plant/" + id, builder.build());
        return mapper.readValue(body, Plant.class);
    }
}

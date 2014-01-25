package client.endpoint;

import client.http.HTTPClient;
import client.http.exception.HTTPClientException;
import client.request.NewPlantRequest;
import com.google.common.base.Joiner;
import model.Plant;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;

public class PlantEndpoint extends Endpoint {

    public PlantEndpoint() {

    }

    public PlantEndpoint(HTTPClient client) {
        super(client);
    }

    public ArrayList<Plant> list(String token) throws IOException, HTTPClientException {
        byte[] body = httpClient.request("GET", Joiner.on("/").join("api", token, "plant").toString());
        return mapper.<ArrayList>readValue(body, new TypeReference<ArrayList<Plant>>() {
        });
    }

    public Plant read(String token, long id) throws IOException, HTTPClientException {
        byte[] body = httpClient.request("GET", Joiner.on("/").join("api", token, "plant", id).toString());
        return mapper.readValue(body, Plant.class);
    }

    public void create(String token, NewPlantRequest plant) throws IOException, HTTPClientException {
        byte[] input = new ObjectMapper().writeValueAsBytes(plant);
        httpClient.request("POST", Joiner.on("/").join("api", token, "plant").toString(), input);
    }
}

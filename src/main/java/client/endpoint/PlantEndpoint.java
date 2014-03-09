package client.endpoint;

import client.model.Plant;
import client.request.NewPlantRequest;
import com.google.common.base.Joiner;
import com.okrest.http.HTTPClient;
import com.okrest.http.HTTPException;
import com.okrest.http.HTTPMethod;
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

    public ArrayList<Plant> list(String token) throws IOException, HTTPException {
        byte[] body = httpClient.request(HTTPMethod.GET, Joiner.on("/").join("api", token, "plant").toString());
        return mapper.<ArrayList>readValue(body, new TypeReference<ArrayList<Plant>>() {
        });
    }

    public Plant read(String token, long id) throws IOException, HTTPException {
        byte[] body = httpClient.request(HTTPMethod.GET, Joiner.on("/").join("api", token, "plant", id).toString());
        return mapper.readValue(body, Plant.class);
    }

    public void create(String token, NewPlantRequest plant) throws IOException, HTTPException {
        byte[] input = new ObjectMapper().writeValueAsBytes(plant);
        httpClient.request(HTTPMethod.POST, Joiner.on("/").join("api", token, "plant").toString(), input);
    }
}

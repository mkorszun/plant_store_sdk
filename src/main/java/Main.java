import client.endpoint.KindEndpoint;
import client.endpoint.PlantEndpoint;
import client.endpoint.TokenEndpoint;
import client.http.exception.HTTPClientException;
import client.request.NewPlantRequest;
import model.Kind;
import model.Plant;
import model.Token;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String... args) throws IOException, HTTPClientException {

        // Get token (authorize with service username / password)
        Token token = new TokenEndpoint().getToken("EMAIL", "PASSWORD");
        System.out.println(token.getToken());

        // List all user plants
        ArrayList<Plant> plants = new PlantEndpoint().list(token.getToken());
        for (Plant p : plants) {
            System.out.println(p.getDescription());
        }

        // Details of specific plant
        Plant plant = new PlantEndpoint().read(token.getToken(), 2);
        System.out.println(plant.getName());

        // Create plant
        NewPlantRequest request = new NewPlantRequest("stokrotka", "blabla", 8);
        new PlantEndpoint().create(token.getToken(), request);

        // List all kinds
        ArrayList<Kind> kinds = new KindEndpoint().list(token.getToken());
        for (Kind k : kinds) {
            System.out.println(k.getName());
        }

    }
}

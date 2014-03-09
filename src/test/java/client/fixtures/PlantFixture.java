package client.fixtures;


import org.json.simple.JSONObject;

public class PlantFixture {

    private long id;
    private String name;
    private String description;
    private JSONObject kind;

    public PlantFixture withId(long id) {
        this.id = id;
        return this;
    }

    public PlantFixture withName(String name) {
        this.name = name;
        return this;
    }

    public PlantFixture withDescription(String description) {
        this.description = description;
        return this;
    }

    public PlantFixture withKind(JSONObject kind) {
        this.kind = kind;
        return this;
    }

    public JSONObject build() {
        JSONObject plant = new JSONObject();
        plant.put("id", id);
        plant.put("name", name);
        plant.put("description", description);
        plant.put("global_kind", kind);
        return plant;
    }
}

package client.fixtures;

import org.json.simple.JSONObject;

public class NewPlantRequestFixture {

    private String name;
    private String description;
    private long kindId;

    public NewPlantRequestFixture withName(String name) {
        this.name = name;
        return this;
    }

    public NewPlantRequestFixture withDescription(String description) {
        this.description = description;
        return this;
    }

    public NewPlantRequestFixture withKindId(long kindId) {
        this.kindId = kindId;
        return this;
    }

    public String build() {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("description", description);
        obj.put("kindId", kindId);
        return obj.toJSONString();
    }
}

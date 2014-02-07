package client.request;

import org.codehaus.jackson.annotate.JsonProperty;

public class NewPlantRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("global_kind_id")
    private long kindId;

    public NewPlantRequest(String name, String description, long kindId) {
        this.name = name;
        this.description = description;
        this.kindId = kindId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getKindId() {
        return kindId;
    }
}

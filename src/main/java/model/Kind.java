package model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

public class Kind implements Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("latin_name")
    private String latinName;

    @JsonProperty("global_treatment")
    private Treatment treatment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }
}

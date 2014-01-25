package model;

import org.json.simple.JSONObject;

public class KindFixture {

    private int id;
    private String name;
    private String latinName;
    private JSONObject treatment;


    public JSONObject build() {
        JSONObject kind = new JSONObject();
        kind.put("id", id);
        kind.put("name", name);
        kind.put("latin_name", latinName);
        kind.put("global_treatment", treatment);
        return kind;
    }

    public KindFixture withId(int id) {
        this.id = id;
        return this;
    }

    public KindFixture withName(String name) {
        this.name = name;
        return this;
    }

    public KindFixture withLatinName(String latinName) {
        this.latinName = latinName;
        return this;
    }

    public KindFixture withTreatment(JSONObject treatment) {
        this.treatment = treatment;
        return this;
    }
}

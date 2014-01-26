package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class KindsListBuilder {

    public static String build() {
        JSONObject treatment = new TreatmentFixture()
                .withWatering("moderate")
                .withInsolation("indirect")
                .withTemperatureMin(14)
                .withTemperatureMax(21)
                .withHumidity("low")
                .withComment(null)
                .build();

        JSONObject kind = new KindFixture()
                .withId(1)
                .withName("Gerbera")
                .withLatinName("Gerbera jamesonii")
                .withTreatment(treatment)
                .build();

        JSONArray resp = new JSONArray();
        resp.add(kind);
        return resp.toJSONString();
    }
}

package model.builders;

import model.KindFixture;
import model.TreatmentFixture;
import org.json.simple.JSONObject;

public class KindBuilder {

    public static JSONObject buildKind() {
        JSONObject treatment = new TreatmentFixture()
                .withWatering("moderate")
                .withInsolation("indirect")
                .withTemperatureMin(14)
                .withTemperatureMax(21)
                .withHumidity("low")
                .withComment(null)
                .build();

        return new KindFixture()
                .withId(1)
                .withName("Gerbera")
                .withLatinName("Gerbera jamesonii")
                .withTreatment(treatment)
                .build();
    }
}

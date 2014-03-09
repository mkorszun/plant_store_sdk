package client.fixtures.builders;

import client.fixtures.KindFixture;
import client.fixtures.TreatmentFixture;
import org.json.simple.JSONObject;

public class KindBuilder {

    public static JSONObject buildKind() {
        JSONObject treatment = new TreatmentFixture()
                .withWateringSeason("moderate")
                .withWateringRest("plentiful")
                .withDryBetweenWateringInSeason(true)
                .withDryBetweenWateringInRest(false)
                .withInsolation("indirect")
                .withSeasonTempMin(14)
                .withSeasonTempMax(21)
                .withRestTempMin(10)
                .withRestTempMax(16)
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

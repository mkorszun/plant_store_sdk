package model;

import org.json.simple.JSONObject;

public class TreatmentFixture {

    private String watering;
    private String insolation;
    private int temperatureMin;
    private int temperatureMax;
    private String humidity;
    private String comment;

    public JSONObject build() {
        JSONObject treatment = new JSONObject();
        treatment.put("watering", watering);
        treatment.put("insolation", insolation);
        treatment.put("temperature_min", temperatureMin);
        treatment.put("temperature_max", temperatureMax);
        treatment.put("humidity", humidity);
        treatment.put("comment", comment);
        return treatment;
    }

    public TreatmentFixture withWatering(String watering) {
        this.watering = watering;
        return this;
    }

    public TreatmentFixture withInsolation(String insolation) {
        this.insolation = insolation;
        return this;
    }

    public TreatmentFixture withTemperatureMin(int temperatureMin) {
        this.temperatureMin = temperatureMin;
        return this;
    }

    public TreatmentFixture withTemperatureMax(int temperatureMax) {
        this.temperatureMax = temperatureMax;
        return this;
    }

    public TreatmentFixture withHumidity(String humidity) {
        this.humidity = humidity;
        return this;
    }

    public TreatmentFixture withComment(String comment) {
        this.comment = comment;
        return this;
    }
}

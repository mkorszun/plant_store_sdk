package client.fixtures;

import org.json.simple.JSONObject;

public class TreatmentFixture {

    private String wateringSeason;
    private String wateringRest;
    private boolean dryBetweenWateringInSeason;
    private boolean dryBetweenWateringInRest;
    private String insolation;
    private int seasonTempMin;
    private int seasonTempMax;
    private int restTempMin;
    private int restTempMax;
    private String humidity;
    private String comment;

    public JSONObject build() {
        JSONObject treatment = new JSONObject();
        treatment.put("watering_season", wateringSeason);
        treatment.put("watering_rest", wateringRest);
        treatment.put("dry_between_watering_season", dryBetweenWateringInSeason);
        treatment.put("dry_between_watering_rest", dryBetweenWateringInRest);
        treatment.put("insolation", insolation);
        treatment.put("season_temp_min", seasonTempMin);
        treatment.put("season_temp_max", seasonTempMax);
        treatment.put("rest_temp_min", restTempMin);
        treatment.put("rest_temp_max", restTempMax);
        treatment.put("humidity", humidity);
        treatment.put("comment", comment);
        return treatment;
    }

    public TreatmentFixture withWateringSeason(String watering) {
        this.wateringSeason = watering;
        return this;
    }

    public TreatmentFixture withWateringRest(String watering) {
        this.wateringRest = watering;
        return this;
    }

    public TreatmentFixture withDryBetweenWateringInSeason(boolean dry) {
        this.dryBetweenWateringInSeason = dry;
        return this;
    }

    public TreatmentFixture withDryBetweenWateringInRest(boolean dry) {
        this.dryBetweenWateringInRest = dry;
        return this;
    }


    public TreatmentFixture withInsolation(String insolation) {
        this.insolation = insolation;
        return this;
    }

    public TreatmentFixture withSeasonTempMin(int temp) {
        this.seasonTempMin = temp;
        return this;
    }

    public TreatmentFixture withSeasonTempMax(int temp) {
        this.seasonTempMax = temp;
        return this;
    }

    public TreatmentFixture withRestTempMin(int temp) {
        this.restTempMin = temp;
        return this;
    }

    public TreatmentFixture withRestTempMax(int temp) {
        this.restTempMax = temp;
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

package client.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

public class Treatment implements Serializable {

    @JsonProperty("watering_season")
    private String wateringSeason;

    @JsonProperty("watering_rest")
    private String wateringRest;

    @JsonProperty("dry_between_watering_season")
    private boolean dryBetweenWateringInSeason;

    @JsonProperty("dry_between_watering_rest")
    private boolean dryBetweenWateringInRest;

    @JsonProperty("insolation")
    private String insolation;

    @JsonProperty("season_temp_min")
    private int seasonTempMin;

    @JsonProperty("season_temp_max")
    private int seasonTempMax;

    @JsonProperty("rest_temp_min")
    private int restTempMin;

    @JsonProperty("rest_temp_max")
    private int restTempMax;

    @JsonProperty("humidity")
    private String humidity;

    @JsonProperty("comment")
    private String comment;

    public String getWateringSeason() {
        return wateringSeason;
    }

    public void setWateringSeason(String wateringSeason) {
        this.wateringSeason = wateringSeason;
    }

    public String getWateringRest() {
        return wateringRest;
    }

    public void setWateringRest(String wateringRest) {
        this.wateringRest = wateringRest;
    }

    public boolean isDryBetweenWateringInSeason() {
        return dryBetweenWateringInSeason;
    }

    public void setDryBetweenWateringInSeason(boolean dryBetweenWateringInSeason) {
        this.dryBetweenWateringInSeason = dryBetweenWateringInSeason;
    }

    public boolean isDryBetweenWateringInRest() {
        return dryBetweenWateringInRest;
    }

    public void setDryBetweenWateringInRest(boolean dryBetweenWateringInRest) {
        this.dryBetweenWateringInRest = dryBetweenWateringInRest;
    }

    public String getInsolation() {
        return insolation;
    }

    public void setInsolation(String insolation) {
        this.insolation = insolation;
    }

    public int getSeasonTempMin() {
        return seasonTempMin;
    }

    public void setSeasonTempMin(int seasonTempMin) {
        this.seasonTempMin = seasonTempMin;
    }

    public int getSeasonTempMax() {
        return seasonTempMax;
    }

    public void setSeasonTempMax(int seasonTempMax) {
        this.seasonTempMax = seasonTempMax;
    }

    public int getRestTempMin() {
        return restTempMin;
    }

    public void setRestTempMin(int restTempMin) {
        this.restTempMin = restTempMin;
    }

    public int getRestTempMax() {
        return restTempMax;
    }

    public void setRestTempMax(int restTempMax) {
        this.restTempMax = restTempMax;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

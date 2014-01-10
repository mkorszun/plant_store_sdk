package model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

public class Treatment implements Serializable {

    @JsonProperty("watering")
    private String watering;

    @JsonProperty("insolation")
    private String insolation;

    @JsonProperty("temperature_min")
    private int temperatureMin;

    @JsonProperty("temperature_max")
    private int temperatureMax;

    @JsonProperty("humidity")
    private String humidity;

    @JsonProperty("comment")
    private String comment;

    public String getWatering() {
        return watering;
    }

    public void setWatering(String watering) {
        this.watering = watering;
    }

    public String getInsolation() {
        return insolation;
    }

    public void setInsolation(String insolation) {
        this.insolation = insolation;
    }

    public int getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(int temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public int getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(int temperatureMax) {
        this.temperatureMax = temperatureMax;
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

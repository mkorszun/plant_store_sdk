package model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

public class Token implements Serializable {

    @JsonProperty("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package client.fixtures;

import org.json.simple.JSONObject;

public class TokenFixture {

    private String token;

    public TokenFixture withToken(String token) {
        this.token = token;
        return this;
    }

    public String build() {
        JSONObject error = new JSONObject();
        error.put("token", token);
        return error.toJSONString();
    }
}

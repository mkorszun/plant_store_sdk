package client.utils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class QueryParamsBuilder {

    Map<String, Object> params = new HashMap<String, Object>();

    public QueryParamsBuilder set(String key, String val) {
        params.put(key, URLEncoder.encode(val));
        return this;
    }

    public QueryParamsBuilder set(String key, long val) {
        set(key, Long.toString(val));
        return this;
    }

    public QueryParamsBuilder set(String key, boolean val) {
        set(key, String.valueOf(val));
        return this;
    }

    public Map<String, Object> build() {
        return params;
    }
}

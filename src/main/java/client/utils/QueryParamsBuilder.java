package client.utils;

import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MultivaluedMap;
import java.net.URLEncoder;

public class QueryParamsBuilder {

    MultivaluedMap<String, String> params = new MultivaluedMapImpl();

    public QueryParamsBuilder set(String key, String val) {
        params.add(key, URLEncoder.encode(val));
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

    public MultivaluedMap build() {
        return params;
    }
}

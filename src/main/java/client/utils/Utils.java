package client.utils;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Utils {

    public static URI buildURI(String backend, String resource, MultivaluedMap<String, String> params) {
        UriBuilder builder = UriBuilder.fromPath(backend);
        builder.path(resource);
        for (String k : params.keySet()) {
            builder.queryParam(k, params.getFirst(k));
        }
        return builder.build();
    }
}

package client.utils;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Map;

public class Utils {

    public static URI buildURI(String backend, String resource) {

        UriBuilder builder = UriBuilder.fromPath(backend);
        builder.path(resource);
        return builder.build();
    }

    public static URI buildURI(String backend, String resource, Map<String, String> params) {
        UriBuilder builder = UriBuilder.fromPath(backend);
        builder.path(resource);

        for (Map.Entry<String, String> e : params.entrySet()) {
            builder.queryParam(e.getKey(), e.getValue());
        }

        return builder.build();
    }
}

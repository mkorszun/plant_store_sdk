package client.http;

import client.http.exception.HTTPClientException;
import client.utils.Utils;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkHttpClient;

import javax.ws.rs.core.MultivaluedMap;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;

public class HTTPClient {

    private static final String BACKEND = "http://localhost:3000";
    private static final int MAX_IDLE_CONNECTIONS = 20;
    private static final long KEEP_ALIVE_DURATION_MS = 300000L;

    private OkHttpClient client;

    public HTTPClient() {
        this.client = new OkHttpClient();
        this.client.setConnectionPool(new ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALIVE_DURATION_MS));
    }

    public byte[] request(String resource, MultivaluedMap<String, String> params) throws HTTPClientException, IOException {
        HttpURLConnection connection = null;
        try {
            URI uri = Utils.buildURI(BACKEND, resource, params);
            connection = client.open(uri.toURL());
            return read(connection);
        } catch (MalformedURLException e) {
            throw new HTTPClientException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private byte[] read(HttpURLConnection connection) throws IOException, HTTPClientException {
        if (connection.getResponseCode() == 200 || connection.getResponseCode() == 201) {
            return readFully(connection.getInputStream());
        } else {
            throw new HTTPClientException(connection.getResponseMessage());
        }
    }

    private byte[] readFully(InputStream in) throws IOException, HTTPClientException {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int count; (count = in.read(buffer)) != -1; ) {
                out.write(buffer, 0, count);
            }
            return out.toByteArray();
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
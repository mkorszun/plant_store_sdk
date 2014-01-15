package client.http;

import client.http.exception.HTTPClientException;
import client.utils.Utils;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkHttpClient;

import javax.ws.rs.core.MultivaluedMap;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;

public class HTTPClient {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String BACKEND = "https://staging-plantstore.cloudcontrolled.com";

    private static final int MAX_IDLE_CONNECTIONS = 20;
    private static final long KEEP_ALIVE_DURATION_MS = 300000L;

    private OkHttpClient client;

    public HTTPClient() {
        this.client = new OkHttpClient();
        this.client.setConnectionPool(new ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALIVE_DURATION_MS));
    }

    public byte[] request(String method, String resource, byte[] body, MultivaluedMap<String, String> params) throws IOException, HTTPClientException {

        OutputStream out = null;
        HttpURLConnection connection = null;

        try {
            URI uri = Utils.buildURI(BACKEND, resource, params);
            connection = client.open(uri.toURL());
            connection.setRequestMethod(method);
            connection.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
            out = connection.getOutputStream();
            out.write(body);
            return read(connection);
        } catch (MalformedURLException e) {
            throw new HTTPClientException(e);
        } finally {

            if (out != null) {
                out.close();
            }

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public byte[] request(String method, String resource, MultivaluedMap<String, String> params) throws IOException, HTTPClientException {

        HttpURLConnection connection = null;

        try {
            URI uri = Utils.buildURI(BACKEND, resource, params);
            connection = client.open(uri.toURL());
            connection.setRequestMethod(method);
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
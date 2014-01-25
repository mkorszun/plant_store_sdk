package client.http;

import client.http.exception.HTTPClientException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class HTTPStream {

    private HttpURLConnection connection;

    public HTTPStream(HttpURLConnection connection) {
        this.connection = connection;
    }

    public byte[] read() throws IOException, HTTPClientException {
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

package client.http;

import client.http.exception.HTTPClientException;
import client.utils.Utils;
import model.Error;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;

import static client.utils.Utils.readBytes;

public class HTTPStream {

    public static final String APPLICATION_JSON = "application/json";

    private HttpURLConnection connection;
    protected ObjectMapper mapper;

    public HTTPStream(HttpURLConnection connection) {
        this.connection = connection;
        this.mapper = new ObjectMapper();
    }

    public byte[] read() throws IOException, HTTPClientException {
        if (connection.getResponseCode() == 200 || connection.getResponseCode() == 201) {
            return readBytes(connection.getInputStream());
        } else {
            return readError();
        }
    }

    private byte[] readError() throws IOException, HTTPClientException {
        if (Utils.in(connection.getContentType(), APPLICATION_JSON)) {
            byte[] errorBody = readBytes(connection.getErrorStream());
            Error error = mapper.readValue(errorBody, Error.class);
            throw new HTTPClientException(error.getError());
        } else {
            throw new HTTPClientException(connection.getResponseMessage());
        }
    }
}

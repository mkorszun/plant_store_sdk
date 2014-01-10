package client.http.exception;

public class HTTPClientException extends Exception {

    public HTTPClientException(String msg) {
        super(msg);
    }

    public HTTPClientException(Throwable t) {
        super(t);
    }
}

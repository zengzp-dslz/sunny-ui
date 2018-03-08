package io.sunny.common.utils.http;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Component
public class SyncHttpClient {
    private CloseableHttpClient client;

    public SyncHttpClient(CloseableHttpClient client) {
        this.client = client;
    }

    public CloseableHttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) throws IOException {
        return client.execute(target, request, context);
    }

    public CloseableHttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException {
        return client.execute(request, context);
    }

    public CloseableHttpResponse execute(HttpUriRequest request) throws IOException {
        return client.execute(request);
    }

    public CloseableHttpResponse execute(HttpHost target, HttpRequest request) throws IOException {
        return client.execute(target, request);
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException {
        return client.execute(request, responseHandler);
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler,
                         HttpContext context) throws IOException {
        return client.execute(request, responseHandler, context);
    }

    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler)
            throws IOException {
        return client.execute(target, request, responseHandler);
    }

    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler,
                         HttpContext context) throws IOException {
        return client.execute(target, request, responseHandler, context);
    }

    @PreDestroy
    public void close() throws IOException {
        client.close();
    }
}

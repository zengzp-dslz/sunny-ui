package io.sunny.common.utils.http;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
    private static SyncHttpClient staticSyncClient;

    @Autowired
    public HttpUtil(SyncHttpClient client) {
        staticSyncClient = client;
    }

    public static <T> T sync(HttpParam param, ResponseHandler<? extends T> responseHandler)
            throws IOException {
        LOGGER.debug("Http sync param: {}", param);
        if (param.getMethod() == HttpParam.Method.GET) {
            return staticSyncClient.execute(handleHttpGet(param), responseHandler);
        } else {
            return staticSyncClient.execute(handleHttpPost(param), responseHandler);
        }
    }

    public static String sync(HttpParam param) throws IOException {
        return sync(param, new BasicResponseHandler());
    }

    private static RequestConfig buildRequestConfig(HttpParam param) {
        int timeout = param.getTimeout();
        return RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .build();
    }

    private static String contentType(HttpParam param) {
        return param.getContentType() + "; charset=" + param.getCharset().name();
    }

    private static String encode(String str, HttpParam param) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, param.getCharset().name());
    }

    public static HttpGet handleHttpGet(HttpParam param) throws UnsupportedEncodingException {
        if (param.getBodyType() == HttpParam.BodyType.KEYVALUE) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : param.getKeyValueData().entrySet()) {
                sb.append(encode(entry.getKey(), param)).append("=")
                        .append(encode(entry.getValue(), param)).append("&");
            }
            HttpGet httpGet = new HttpGet(param.getUrl() + "?" + sb.substring(0, sb.length() - 1));
            httpGet.setHeader("Content-Type", contentType(param));
            httpGet.setConfig(buildRequestConfig(param));
            return httpGet;
        } else {
            throw new UnsupportedOperationException("HttpGet BodyType not supported: " + param.getBodyType());
        }
    }

    public static HttpPost handleHttpPost(HttpParam param) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(param.getUrl());
        if (param.getBodyType() == HttpParam.BodyType.KEYVALUE) {
            List<NameValuePair> nvps = new ArrayList<>();
            param.getKeyValueData().forEach((key, value) -> nvps.add(new BasicNameValuePair(key, value)));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, param.getCharset()));
        } else if (param.getBodyType() == HttpParam.BodyType.STRING) {
            httpPost.setEntity(new StringEntity(param.getStringData(),
                    ContentType.create(param.getContentType(), param.getCharset())));
        } else if (param.getBodyType() == HttpParam.BodyType.BINARY) {
            httpPost.setEntity(new ByteArrayEntity(param.getBinaryData()));
        } else {
            throw new UnsupportedOperationException("HttpPost BodyType not supported: " + param.getBodyType());
        }
        httpPost.setConfig(buildRequestConfig(param));
        return httpPost;
    }
}

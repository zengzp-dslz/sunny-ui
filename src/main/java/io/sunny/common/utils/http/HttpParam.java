package io.sunny.common.utils.http;

import java.nio.charset.Charset;
import java.util.Map;

public class HttpParam {
    private Method method = Method.POST;
    private BodyType bodyType = BodyType.KEYVALUE;
    private String url;
    private String contentType = "application/x-www-form-urlencoded";
    private Charset charset = Charset.forName("UTF-8");
    private int timeout = 5000;
    private byte[] binaryData;
    private Map<String, String> keyValueData;
    private String stringData;

    public enum Method {
        GET, POST
    }

    public enum BodyType {
        BINARY, KEYVALUE, STRING
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = Charset.forName(charset);
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public byte[] getBinaryData() {
        return binaryData;
    }

    public void setBinaryData(byte[] binaryData) {
        this.binaryData = binaryData;
    }

    public Map<String, String> getKeyValueData() {
        return keyValueData;
    }

    public void setKeyValueData(Map<String, String> keyValueData) {
        this.keyValueData = keyValueData;
    }

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }

    @Override
    public String toString() {
        return "HttpParam{" +
                "method=" + method +
                ", bodyType=" + bodyType +
                ", url='" + url + '\'' +
                ", contentType='" + contentType + '\'' +
                ", charset=" + charset +
                ", timeout=" + timeout +
                ", binaryData=" + (binaryData == null ? "null" : ("length: " + binaryData.length)) +
                ", keyValueData=" + keyValueData +
                ", stringData='" + stringData + '\'' +
                '}';
    }
}

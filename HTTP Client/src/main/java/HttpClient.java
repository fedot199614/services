import kong.unirest.*;

import java.util.Map;

public class HttpClient extends Unirest {
    private static HttpClient HTTP_CLIENT_INSTANCE = null;

    private HttpClient() {
        config().setDefaultHeader("Accept", "*/*");
        config().connectTimeout(10000);
        config().socketTimeout(10000);
    }

    public static HttpClient getHttpClientInstance() {
        if (HTTP_CLIENT_INSTANCE == null) {
            HTTP_CLIENT_INSTANCE = new HttpClient();
            return HTTP_CLIENT_INSTANCE;
        }
        return HTTP_CLIENT_INSTANCE;
    }

    public void setDefaultBaseUrl(String url) {
        config().defaultBaseUrl(url);
    }

    public void setDefaultHeaders(Map<String, String> headers) {
        headers.forEach((k, v) -> {
            config().addDefaultHeader(k, v);
        });
    }


    public Config getConfig() {
        return config();
    }

    public GetRequest getRequest(String url, Map<String, Object> queryParam) {
        return Unirest.get(url).queryString(queryParam);
    }

    public GetRequest getRequest(String url) {
        return Unirest.get(url);
    }

    public GetRequest getRequest(String url, Map<String, String> headers, Map<String, Object> queryParam) {
        return Unirest.get(url).headers(headers).queryString(queryParam);
    }

    public RequestBodyEntity postRequest(String url, Object body) {
        return Unirest.post(url).body(body);
    }

    public RequestBodyEntity postRequest(String url, Map<String, String> headers,String body) {
        return Unirest.post(url).headers(headers).body(body);
    }

    public MultipartBody postSimpleFormRequest(String url, Map<String, Object> fields) {
        return Unirest.post(url).fields(fields);
    }

    public HttpResponse deleteRequest(String url) {
        return Unirest.delete(url).asEmpty();
    }

    public RequestBodyEntity putRequest(String url, String body) {
        return Unirest.put(url).body(body);
    }

    public RequestBodyEntity putRequest(String url, Map<String, String> headers, String body) {
        return Unirest.put(url).headers(headers).body(body);
    }


}

import java.util.HashMap;
import java.util.Map;

public class BuilderDesignPattern {
    public static void main(String[] args) {
        HttpRequest getRequest = new HttpRequest.Builder()
                .url("https://api.example.com/users")
                .method("GET")
                .header("Accept", "application/json")
                .build();

        HttpRequest postRequest = new HttpRequest.Builder()
                .url("https://api.example.com/users")
                .method("POST")
                .header("Content-Type", "application/json")
                .body("{\"name\":\"Sneha\"}")
                .build();

        System.out.println(getRequest);
        System.out.println(postRequest);
    }
}

class HttpRequest {
    private String url;
    private String method;
    private Map<String, String> headers;
    private String body;

    private HttpRequest() {
        this.method = "GET";
        this.headers = new HashMap<>();
    }

    public static class Builder {
        private HttpRequest request = new HttpRequest();

        public Builder url(String url) {
            request.url = url;
            return this;
        }

        public Builder method(String method) {
            request.method = method;
            return this;
        }

        public Builder header(String key, String value) {
            request.headers.put(key, value);
            return this;
        }

        public Builder body(String body) {
            request.body = body;
            return this;
        }

        public HttpRequest build() {
            return request;
        }
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}

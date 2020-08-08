import com.google.common.collect.ImmutableMap;
import kong.unirest.GetRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class HttpClientTest {
    private HttpClient httpClient;

    @BeforeClass
    public void init() {
        httpClient = HttpClient.getHttpClientInstance();
        httpClient.setDefaultBaseUrl("https://reqres.in");
    }

    @Test
    public void getTest() {
        System.out.println("GET request test");
        HttpResponse<JsonNode> response = httpClient.getRequest("/api/users?page=2").asJson();
        System.out.println("Status code -> "+response.getStatus());
        Assertions.assertThat(response.getStatus()).isEqualTo(200); //status code check

        String actualEmail = response.getBody().getObject().getJSONArray("data")
                .getJSONObject(0).getString("email");
        System.out.println("Body test actual email  -> "+actualEmail);
        Assertions.assertThat(actualEmail).isEqualTo("michael.lawson@reqres.in");
    }



    @Test
    public void postTest() {
        System.out.println("POST request test");
        HttpResponse<JsonNode> response = httpClient.postRequest("/api/users",
                ImmutableMap.of("Content-Type","application/json"),
                "{\"name\": \"fedot\",\"job\": \"tester\"}")
                .asJson();
        System.out.println("Status code -> "+response.getStatus());
        Assertions.assertThat(response.getStatus()).isEqualTo(201); //status code check
        System.out.println(response.getBody().toPrettyString());
        String actualName = response.getBody().getObject().getString("name");
        System.out.println("Body test actual name  -> "+actualName);
        Assertions.assertThat(actualName).isEqualTo("fedot");
    }

    @Test
    public void putTest() {
        System.out.println("PUT request test");
        HttpResponse<JsonNode> response = httpClient.putRequest("/api/users/2",
                ImmutableMap.of("Content-Type","application/json"),
                "{\"name\": \"fedot2\",\"job\": \"zion resident\"}")
                .asJson();
        System.out.println("Status code -> "+response.getStatus());
        Assertions.assertThat(response.getStatus()).isEqualTo(200); //status code check
        System.out.println(response.getBody().toPrettyString());
        String actualName = response.getBody().getObject().getString("name");
        System.out.println("Body test actual name  -> "+actualName);
        Assertions.assertThat(actualName).isEqualTo("fedot2");
    }

    @Test
    public void deleteTest() {
        System.out.println("DELETE request test");
        HttpResponse response = httpClient.deleteRequest("/api/users/2");
        System.out.println("Status code -> "+response.getStatus());
        Assertions.assertThat(response.getStatus()).isEqualTo(204); //status code check
    }

}

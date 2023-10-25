package local.home.demo.test;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.path.json.config.JsonPathConfig;
import local.home.demo.test.pojo.RequestBody;
import local.home.demo.test.pojo.ResponseBody;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("POST test")
public class ApiTestPost extends ApiTestBase {

    private static final String path = "/post";

    @Test(description = "POST with text body")
    @Story("POST with text body")
    @Severity(SeverityLevel.CRITICAL)
    public void postTextBodyTest() {
        var response = given().when().contentType(ContentType.TEXT).body("foo").post(path);
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().get("data").toString()).isEqualTo("foo");
    }

    @Test(description = "POST with JSON body")
    @Story("POST with JSON body")
    @Severity(SeverityLevel.CRITICAL)
    public void postJsonBodyTest() {
        var body = RequestBody.create();
        var response = given().when().contentType(ContentType.JSON).body(body).post(path);
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body().as(ResponseBody.class).getData()).isEqualTo(body);
    }

    @Test(description = "POST with multipart form data")
    @Story("POST with multipart form data")
    @Severity(SeverityLevel.CRITICAL)
    public void postFormDataTest() {
        var response = given().when().contentType(ContentType.MULTIPART).multiPart("foo", "bar").post(path);
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().get("form.foo").toString()).isEqualTo("bar");
    }

    @Test(description = "POST endpoint with incorrect method")
    @Story("POST endpoint with incorrect method")
    @Severity(SeverityLevel.NORMAL)
    public void postIncorrectMethodTest() {
        given().when().get(path).then().statusCode(405);
        given().when().put(path).then().statusCode(405);
        given().when().patch(path).then().statusCode(405);
        given().when().delete(path).then().statusCode(405);
    }

}

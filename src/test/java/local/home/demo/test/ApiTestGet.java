package local.home.demo.test;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("GET test")
public class ApiTestGet extends ApiTestBase {

    private static final String path = "/get";

    @Test(description = "GET with parameter")
    @Story("GET with parameter")
    @Severity(SeverityLevel.CRITICAL)
    public void getParamTest() {
        var response = given().when().param("foo", "bar").get(path);
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().get("args.foo").toString()).isEqualTo("bar");
    }

    @Test(description = "GET with empty parameter")
    @Story("GET with empty parameter")
    @Severity(SeverityLevel.CRITICAL)
    public void getEmptyValueParamTest() {
        var response = given().when().param("foo").get(path);
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().get("args.foo").toString()).isEqualTo("");
    }

    @Test(description = "GET endpoint with incorrect method")
    @Story("GET endpoint with incorrect method")
    @Severity(SeverityLevel.NORMAL)
    public void getIncorrectMethodTest() {
        given().when().post(path).then().statusCode(405);
        given().when().put(path).then().statusCode(405);
        given().when().patch(path).then().statusCode(405);
        given().when().delete(path).then().statusCode(405);
    }

}

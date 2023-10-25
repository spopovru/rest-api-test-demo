package local.home.demo.test;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import local.home.demo.test.pojo.AuthenticationResult;
import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("AUTH test")
public class ApiAuthGet extends ApiTestBase {

    @Test(description = "AUTH bearer")
    @Story("AUTH bearer")
    @Severity(SeverityLevel.CRITICAL)
    public void authBearerTest() {
        var response = given().when().header("Authorization", "Bearer foo").get("/bearer");
        assertThat(response.statusCode()).isEqualTo(200);
        Map<String, Object> authenticationResult = response.body().path("");
        assertThat(authenticationResult.get("authenticated")).isEqualTo(true);
        assertThat(authenticationResult.get("token")).isEqualTo("foo");
    }

    @Test(description = "AUTH bearer unauthorised")
    @Story("AUTH bearer unauthorized")
    @Severity(SeverityLevel.CRITICAL)
    public void authBearerTestUnauthorized() {
        var response = given().when().get("/bearer");
        assertThat(response.statusCode()).isEqualTo(401);
    }

    @Test(description = "AUTH basic")
    @Story("AUTH basic")
    @Severity(SeverityLevel.CRITICAL)
    public void authBasicTest() {
        var response = given().when().auth().basic("user", "password").get("/basic-auth/user/password");
        assertThat(response.statusCode()).isEqualTo(200);
        Map<String, Object> authenticationResult = response.body().path("");
        assertThat(authenticationResult.get("authenticated")).isEqualTo(true);
        assertThat(authenticationResult.get("user")).isEqualTo("user");
    }

    @Test(description = "AUTH basic unauthorised")
    @Story("AUTH basic unauthorized")
    @Severity(SeverityLevel.CRITICAL)
    public void authBasicTestUnauthorized() {
        var response = given().when().get("/basic-auth/user/password");
        assertThat(response.statusCode()).isEqualTo(401);
    }

}

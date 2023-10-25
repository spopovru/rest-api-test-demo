package local.home.demo.test;

import io.qameta.allure.Epic;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import local.home.demo.test.config.ApiTestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeSuite;

@Epic("API Tests")
public class ApiTestBase {

    private static final ApiTestConfig config = ConfigFactory.create(ApiTestConfig.class);

    @BeforeSuite
    public static void setUpAll() {
        RestAssured.baseURI = String.format("%s://%s", config.targetProtocol(), config.targetHost());
        RestAssured.port = config.targetPort();
    }

    protected RequestSpecification given() {
        return RestAssured.given().filter(new AllureRestAssured());
    }

}

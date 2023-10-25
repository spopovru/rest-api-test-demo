package local.home.demo.test;

import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.annotations.Test;

@Feature("Smoke test")
public class ApiTestSmoke extends ApiTestBase {

    @Test(description = "GET method smoke test")
    @Story("GET method smoke test")
    @Severity(SeverityLevel.BLOCKER)
    public void getTest() {
        given().when().get("/get").then().statusCode(200);
    }

    @Test(description = "POST method smoke test")
    @Story("POST method smoke test")
    @Severity(SeverityLevel.BLOCKER)
    public void postTest() {
        given().when().post("/post").then().statusCode(200);
    }

    @Test(description = "PUT method smoke test")
    @Story("PUT method smoke test")
    @Severity(SeverityLevel.BLOCKER)
    public void putTest() {
        given().when().put("/put").then().statusCode(200);
    }

    @Test(description = "PATCH method smoke test")
    @Story("PATCH method smoke test")
    @Severity(SeverityLevel.BLOCKER)
    public void patchTest() {
        given().when().patch("/patch").then().statusCode(200);
    }

    @Test(description = "DELETE method smoke test")
    @Story("DELETE method smoke test")
    @Severity(SeverityLevel.BLOCKER)
    public void deleteTest() {
        given().when().delete("/delete").then().statusCode(200);
    }

}

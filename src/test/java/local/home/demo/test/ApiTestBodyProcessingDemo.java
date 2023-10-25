package local.home.demo.test;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import local.home.demo.test.pojo.demo.Order;
import local.home.demo.test.pojo.demo.OrderItem;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;

@Feature("Body processing demo")
public class ApiTestBodyProcessingDemo extends ApiTestBase {

    private static final String path = "/anything";
    private List<Order> orders = new ArrayList<>();

    @BeforeTest
    public void setUpTest() {
        var order = new Order();
        order.setOrderId("order-1");
        order.getOrderItems().add(new OrderItem("Item 1", 100, 10));
        order.getOrderItems().add(new OrderItem("Item 2", 200, 5));
        order.getOrderItems().add(new OrderItem("Item 2", 300, 20));
        orders.add(order);
        order = new Order();
        order.setOrderId("order-2");
        order.getOrderItems().add(new OrderItem("Item 1", 100, 5));
        order.getOrderItems().add(new OrderItem("Item 2", 200, 10));
        order.getOrderItems().add(new OrderItem("Item 2", 300, 15));
        orders.add(order);
        order = new Order();
        order.setOrderId("order-3");
        order.getOrderItems().add(new OrderItem("Item 1", 100, 1));
        order.getOrderItems().add(new OrderItem("Item 2", 200, 2));
        order.getOrderItems().add(new OrderItem("Item 2", 300, 3));
        orders.add(order);
    }

    @Test(description = "Check response with groovy")
    @Story("Check response with groovy")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkResponseWithGroovy() {
        given().when()
                .contentType(ContentType.MULTIPART)
                .contentType(ContentType.JSON)
                .body(orders)
                .post(path)
                .then()
                .body("json.findAll {true}.orderId", hasItems("order-1", "order-2", "order-3"))
                .body("json.findAll { it.orderId == 'order-1' }.orderItems[0].size()", equalTo(3))
                .body("json.findAll { it.orderId == 'order-2' }.orderItems[0].size()", equalTo(3))
                .body("json.findAll { it.orderId == 'order-3' }.orderItems[0].size()", equalTo(3))
                .body("json.findAll { it.orderId == 'order-1' }.orderItems.quantity[0].sum()", equalTo(35))
                .body("json.findAll { it.orderId == 'order-2' }.orderItems.quantity[0].sum()", equalTo(30))
                .body("json.findAll { it.orderId == 'order-3' }.orderItems.quantity[0].sum()", equalTo(6))
                .body("json.collect {it.orderItems}.collect {it.quantity.sum()}", hasItems(35, 30, 6));
    }

}

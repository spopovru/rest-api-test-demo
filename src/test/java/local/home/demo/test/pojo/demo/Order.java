package local.home.demo.test.pojo.demo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Order {

    private String orderId;
    private List<OrderItem> orderItems = new ArrayList<>();

}

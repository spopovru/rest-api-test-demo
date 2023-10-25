package local.home.demo.test.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestBody {

    private String foo;

    public RequestBody(String json) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(json);
        this.foo = node.get("foo").asText();
    }

    public static RequestBody create() {
        var requestBody = new RequestBody();
        requestBody.setFoo("bar");
        return requestBody;
    }

}

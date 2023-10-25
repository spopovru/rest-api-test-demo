package local.home.demo.test.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonDeserialize
public class AuthenticationResult {

    private Boolean authenticated;
    private String token;

}

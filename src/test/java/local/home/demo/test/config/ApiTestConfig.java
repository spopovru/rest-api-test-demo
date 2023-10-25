package local.home.demo.test.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({
        "classpath:config/ApiTestConfig.properties",
        "system:properties",
        "system:env"
})
public interface ApiTestConfig extends Config {

    @Key("target.protocol")
    @DefaultValue("http")
    String targetProtocol();

    @Key("target.port")
    @DefaultValue("80")
    int targetPort();

    @Key("target.host")
    @DefaultValue("localhost")
    String targetHost();

}

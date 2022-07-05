package us.hyalen.springtemplate.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "standard")
@PropertySource("classpath:i18n/standard-codes.properties")
public class ErrorCodeConfig {
    private String badRequestCode;
    private Integer badRequestHttp;
    private String badRequestMessage;

    private String badGatewayCode;
    private Integer badGatewayHttp;
    private String badGatewayMessage;

    private String internalServerCode;
    private Integer intervalServerHttp;
    private String internalServerMessage;
}

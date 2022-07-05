package us.hyalen.springtemplate.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Data
@Configuration
public class URIConfig {
    @Value("${paymentInitiation.host}")
    private String paymentInitiationHost;

    @Value("${paymentInitiation.basePath}")
    private String paymentInitiationBasePath;

    @Value("${paymentInitiation.payeeEvaluationURL}")
    private String payeeEvaluationURL;
}

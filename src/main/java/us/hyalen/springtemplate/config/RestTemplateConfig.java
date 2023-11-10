package us.hyalen.springtemplate.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import us.hyalen.springtemplate.interceptor.RequestResponseLoggingInterceptor;
import us.hyalen.springtemplate.util.AppRestTemplate;
import us.hyalen.springtemplate.util.RestTemplateDelegate;

import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
public class RestTemplateConfig {
    @Value("${us.hyalen.logger.scrubForSecurity:true}")
    private boolean scrubForSecurity;

    @Bean
    @Qualifier("ppoRestTemplate")
    public AppRestTemplate getAppRestTemplate(
            // final HttpComponentsClientHttpRequestFactory restClientHttpRequestFactory,
            final List<ClientHttpRequestInterceptor> interceptors) {
        final HttpComponentsClientHttpRequestFactory restClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();

        RestTemplateDelegate restTemplateDelegate = new RestTemplateDelegate();
        restTemplateDelegate.setRequestFactory(new BufferingClientHttpRequestFactory(restClientHttpRequestFactory));
        interceptors.add(new RequestResponseLoggingInterceptor(scrubForSecurity));
        restTemplateDelegate.setInterceptors(interceptors);

        return new AppRestTemplate(restTemplateDelegate);
    }
}

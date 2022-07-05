package us.hyalen.springtemplate.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Error handler that returns <code>false</code> for {@link #hasError(ClientHttpResponse)} method regardless of the
 * status of the client #response.
 *
 * <P>
 * It is used in combination with {@link HttpResponseExtractorDelegate} which converts any response of a
 * {@link org.springframework.web.client.RestTemplate} invocation to a
 * {@link us.hyalen.springtemplate.core.dto.ResponseDto} structure.
 * </P>
 */
public class NoExceptionRestTemplateErrorHandler implements ResponseErrorHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoExceptionRestTemplateErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("Response Status: {}", response.getStatusCode());

        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) {
        // This line should never ever be executed
        LOGGER.error("=== #hasError() must return false and this method should never be executed.");
    }
}

package us.hyalen.springtemplate.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import us.hyalen.springtemplate.config.InternalClientErrorType;
import us.hyalen.springtemplate.core.NoExceptionRestTemplateErrorHandler;
import us.hyalen.springtemplate.core.dto.ErrorDto;
import us.hyalen.springtemplate.core.dto.ResponseDto;

import java.io.IOException;
import java.net.URI;

public class RestTemplateDelegate extends RestTemplate {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateDelegate.class);
    protected AppRestTemplateErrorHandlingStrategy appRestTemplateErrorHandlingStrategy;
    public RestTemplateDelegate() {
        setErrorHandler(new NoExceptionRestTemplateErrorHandler());
    }

    public RestTemplateDelegate(AppRestTemplateErrorHandlingStrategy appRestTemplateErrorHandlingStrategy) {
        this();
        this.appRestTemplateErrorHandlingStrategy = appRestTemplateErrorHandlingStrategy;
    }

    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) {
        ClientHttpResponse response = null;

        try {
            ClientHttpRequest  request = createRequest(url, method);

            if (requestCallback != null)
                requestCallback.doWithRequest(request);

            response = request.execute();
            handleResponse(url, method, response);

            return responseExtractor.extractData((response));
        } catch(IOException ex) {
            LOGGER.error("Error while connecting to the server", ex);

            ErrorDto errorDto = new ErrorDto(InternalClientErrorType.CONNECTION_FAIL.name(), "error occurred while calling downstream");

            return (T) new ResponseEntity<ResponseDto<Void>>(ResponseDto.forError(errorDto), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (HttpMessageNotReadableException ex) {
            return null;
        } finally {
            if (response != null)
                response.close();
        }
    }
}

package us.hyalen.springtemplate.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import us.hyalen.springtemplate.core.dto.ResponseDto;

public interface AppRestTemplateErrorHandlingStrategy {
    ResponseDto handleError(String response, HttpStatus httpStatus, HttpHeaders httpHeaders);
}

package us.hyalen.springtemplate.client.core;

import ch.qos.logback.core.net.server.Client;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import us.hyalen.springtemplate.config.ErrorCodeConfig;
import us.hyalen.springtemplate.core.ClientException;
import us.hyalen.springtemplate.core.dto.ErrorDto;
import us.hyalen.springtemplate.core.dto.ResponseDto;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Component
public class ClientResponseHandler {
    private final ErrorCodeConfig errorCodeConfig;

    public <T> ResponseDto<T> checkClientResponse(ResponseEntity<ResponseDto<T>> responseEntity) {
        if (responseEntity.getStatusCode().is2xxSuccessful())
            return responseEntity.getBody();

        Optional<ResponseDto<T>> responseDtoOptional = Optional.ofNullable(responseEntity.getBody());

        if (responseDtoOptional.isPresent()) {
            log.error(
                    "There has been an error from the client. Response is a "
                    + responseEntity.getStatusCode().value()
                    + " status."
            );

            throw ClientException.create(responseDtoOptional.get().getErrors(), responseEntity.getStatusCode());
        } else {
            log.error(
                    "There has been an error from the client. Response is a "
                    + responseEntity.getStatusCode().value()
                    + "status and there is no response body."
            );

            ErrorDto errorDto = new ErrorDto (
                    errorCodeConfig.getInternalServerCode(),
                    errorCodeConfig.getInternalServerMessage(),
                    "There has been an error from the client. Response is a "
                    + responseEntity.getStatusCode()
                    + " and there is no response body."
            );

            throw ClientException.create(Collections.singletonList(errorDto), responseEntity.getStatusCode());
        }
    }
}

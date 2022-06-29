package us.hyalen.springtemplate.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import us.hyalen.springtemplate.core.BadResponseBodyException;
import us.hyalen.springtemplate.core.ClientException;
import us.hyalen.springtemplate.core.NotFoundException;
import us.hyalen.springtemplate.core.dto.ErrorDto;
import us.hyalen.springtemplate.core.dto.ResponseDto;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorCodeConfig errorCodeConfig;

    @ResponseBody
    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ResponseDto<Void>> clientException(ClientException e) {
        return new ResponseEntity<>(ResponseDto.forError(e.getErrorDtos()), e.getHttpStatus());
    }

    @ResponseBody
    @ExceptionHandler(BadResponseBodyException.class)
    public ResponseEntity<ResponseDto<Void>> badResponseBodyException(BadResponseBodyException e) {
        ErrorDto errorDto =
                new ErrorDto(
                        errorCodeConfig.getBadGatewayCode(),
                        errorCodeConfig.getBadGatewayMessage(),
                        e.getMessage()
                );

        return new ResponseEntity<>(
                ResponseDto.forError(errorDto),
                HttpStatus.valueOf(errorCodeConfig.getBadGatewayHttp())
        );
    }
}

package us.hyalen.springtemplate.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a solitary model designed for capturing error details supporting ResponseDto
 */
@Data
@AllArgsConstructor
public class ErrorDto extends Dto {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorDto.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @JsonIgnore
    private ErrorCode errorCode;
    private String code;
    private String message;
    private String internalMessage;
}

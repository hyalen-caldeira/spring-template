package us.hyalen.springtemplate.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import us.hyalen.springtemplate.core.dto.ResponseDto;

import java.net.URI;
import java.util.function.Supplier;

@AllArgsConstructor
@Getter
@Setter
public abstract class ResponseEntitySupplier <T> implements Supplier<ResponseEntity<ResponseDto<T>>> {
    private String url;
    private HttpMethod httpMethod;

    public ResponseEntitySupplier(URI url, HttpMethod httpMethod) {
        this(url.toString(), httpMethod);
    }
}

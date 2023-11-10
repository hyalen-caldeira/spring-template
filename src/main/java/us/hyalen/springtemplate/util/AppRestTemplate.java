package us.hyalen.springtemplate.util;

import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import us.hyalen.springtemplate.core.dto.ResponseDto;

import java.net.URI;

//@AllArgsConstructor
public class AppRestTemplate {
    private RestTemplateRxWrapper restTemplateRxWrapper;
    private RestTemplateDelegate restTemplateDelegate;

    public AppRestTemplate(RestTemplateDelegate restTemplateDelegate) {
        this.restTemplateDelegate = restTemplateDelegate;
        this.restTemplateRxWrapper = new RestTemplateRxWrapperImpl();
    }

    public <T> Single<ResponseEntity<ResponseDto<T>>> exchange(
            URI url,
            HttpMethod method,
            HttpEntity<?> requestEntity,
            ParameterizedTypeReference<ResponseDto<T>> responseType) {

        return restTemplateRxWrapper.lift(new ResponseEntitySupplier<T>(url, method) {
            @Override
            public ResponseEntity<ResponseDto<T>> get() {
                return restTemplateDelegate.exchange(url, method, requestEntity, responseType);
            }
        });
    }
}

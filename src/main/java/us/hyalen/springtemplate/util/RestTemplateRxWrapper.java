package us.hyalen.springtemplate.util;

import io.reactivex.rxjava3.core.Single;
import org.springframework.http.ResponseEntity;
import us.hyalen.springtemplate.core.dto.ResponseDto;

public interface RestTemplateRxWrapper {
    <T> Single<ResponseEntity<ResponseDto<T>>> lift(ResponseEntitySupplier<T> responseEntitySupplier);
}

package us.hyalen.springtemplate.util;

import io.reactivex.rxjava3.core.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import us.hyalen.springtemplate.core.dto.ResponseDto;

public class RestTemplateRxWrapperImpl implements RestTemplateRxWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateRxWrapperImpl.class);

    @Override
    public <T> Single<ResponseEntity<ResponseDto<T>>> lift(ResponseEntitySupplier<T> responseEntitySupplier) {
        return Single.create(singleSubscriber -> {
            if (LOGGER.isTraceEnabled())
                LOGGER.trace("Request executing in : {} Thread", Thread.currentThread().getName());

            singleSubscriber.onSuccess(responseEntitySupplier.get());
        });
    }
}

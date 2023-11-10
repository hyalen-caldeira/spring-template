package us.hyalen.springtemplate.core.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import us.hyalen.springtemplate.client.PaymentParameterClient;
import us.hyalen.springtemplate.client.response.PaymentParameterInnerResponse;
import us.hyalen.springtemplate.core.dto.PaymentParameterDto;
import us.hyalen.springtemplate.core.mapper.PaymentParameterMapper;
import us.hyalen.springtemplate.util.Constants;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentParameterService {
    private final PaymentParameterClient paymentParameterClient;
    private final PaymentParameterMapper paymentParameterMapper;

    @Cacheable(value = Constants.PAYMENT_PARAMETER_CACHE_NAME)
    public PaymentParameterDto getPaymentParameter(String sourceRequestId, String mdmPartyIdentifier) {
        log.info(
                "Cache value not found for key values: {}, {}, {}",
                Constants.PAYMENT_PARAMETER_CACHE_NAME,
                sourceRequestId,
                mdmPartyIdentifier
        );

        PaymentParameterInnerResponse paymentParameterInnerResponse =
                paymentParameterClient.getPaymentParameters(sourceRequestId, mdmPartyIdentifier);

        return paymentParameterMapper.mapResponseToDto(paymentParameterInnerResponse);
    }

}

package us.hyalen.springtemplate.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import us.hyalen.springtemplate.client.response.paymentparameter.PaymentParameterInnerResponse;
import us.hyalen.springtemplate.config.URIConfig;
import io.reactivex.rxjava3.schedulers.Schedulers;
import us.hyalen.springtemplate.core.dto.ResponseDto;
import us.hyalen.springtemplate.util.AppRestTemplate;
import us.hyalen.springtemplate.util.HeaderConstants;
import us.hyalen.springtemplate.util.RESTUtil;

@Component
public class PaymentParameterClient {
    private final URIConfig uriConfig;
    private final AppRestTemplate appRestTemplate;
    private final ClientResponseHandler clientResponseHandler;

    public PaymentParameterClient(
            URIConfig uriConfig,
            @Qualifier("appRestTemplate") AppRestTemplate appRestTemplate,
            ClientResponseHandler clientResponseHandler) {
        this.uriConfig = uriConfig;
        this.appRestTemplate = appRestTemplate;
        this.clientResponseHandler = clientResponseHandler;
    }

    public PaymentParameterInnerResponse getPaymentParameters (String sourceRequestId, String mdmPartyIdentifier) {
        return appRestTemplate
                .exchange (
                        RESTUtil.buildURI (
                                uriConfig.getPaymentInitiationHost(),
                                uriConfig.getPaymentInitiationBasePath(),
                                uriConfig.getPayeeEvaluationURL()),
                        HttpMethod.GET,
                        RESTUtil.createGetHttpEntity(createHttpHeaders(sourceRequestId, mdmPartyIdentifier)),
                        objTypeRef())
                .map(clientResponseHandler::checkClientResponse)
                .observeOn(Schedulers.io())
                .blockingGet()
                .getData();
    }

    private HttpHeaders createHttpHeaders(String sourceRequestId, String mdmPartyIdentifier) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HeaderConstants.Common.SOURCE_REQUEST_ID, sourceRequestId);
        headers.add(HeaderConstants.Inner.MDM_PARTY_IDENTIFIER, mdmPartyIdentifier);

        return headers;
    }

    private ParameterizedTypeReference<ResponseDto<PaymentParameterInnerResponse>> objTypeRef() {
        return new ParameterizedTypeReference<ResponseDto<PaymentParameterInnerResponse>>() {};
    }
}

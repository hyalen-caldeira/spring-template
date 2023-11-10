package us.hyalen.springtemplate.interceptor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public class RequestResponseLoggingInterceptor implements ClientHttpRequestInterceptor {
     private static final String FAILED_TO_LOG = "Failed to log due to an error.";
     private boolean scrubForSecurity;

     public RequestResponseLoggingInterceptor() {
         this.scrubForSecurity = true;
     }

     @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
         logRequest(request, body);
         ClientHttpResponse response = execution.execute(request, body);
         logResponse(response);

         return response;
     }

     private void logRequest(HttpRequest request, byte[] body) throws IOException {
         // TODO
         if (log.isInfoEnabled()) {
             log.info("========== Request Begin ==========");
             log.info("URI    : {}", request.getURI());
             log.info("Method : {}", request.getMethod());

             try {
                 Map<String, String> headers = request.getHeaders().toSingleValueMap();

//                 if (scrubForSecurity)
//                     LoggingUtil.sanitizeHeaders(headers);

                 log.info("Headers : {}", headers);
             } catch (Exception ex) {
                 log.info("Headers : {}", FAILED_TO_LOG);
             }

             try {
//                 Map<Object, Object> requestBodyMap = LoggingUtil.convertByteToObjectMap(body);
//
//                 if (scrubForSecurity)
//                     LoggingUtil.sanitizePayloadBodyMap(requestBodyMap);

//                 log.info("Request Body : {}", requestBodyMap);
             } catch (Exception ex) {
                 log.info("Request Body : {}", FAILED_TO_LOG);
             }

             log.info("========== Request End ==========");
         }
     }

     private void logResponse(ClientHttpResponse response) throws IOException {
         // TODO
     }
}

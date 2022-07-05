package us.hyalen.springtemplate.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
public class RESTUtil {
    private RESTUtil() {}

    public static URI buildURI(String host, String... pathSegments) {
        URI uri = UriComponentsBuilder.fromHttpUrl(host).pathSegment(pathSegments).build().toUri();

        return uri;
    }

    public static <T> HttpEntity<T> createGetHttpEntity(HttpHeaders httpHeaders) {
        return new HttpEntity<>(httpHeaders);
    }
}

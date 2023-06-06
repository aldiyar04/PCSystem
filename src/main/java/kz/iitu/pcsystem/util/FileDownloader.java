package kz.iitu.pcsystem.util;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

public class FileDownloader {
    public static byte[] download(String uri) {
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // Set buffer size to 10MB
                .build();
        WebClient webClient = WebClient.builder()
                .exchangeStrategies(exchangeStrategies)
                .build();
        return webClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();
    }
}

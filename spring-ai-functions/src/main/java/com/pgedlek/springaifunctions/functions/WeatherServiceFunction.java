package com.pgedlek.springaifunctions.functions;

import com.pgedlek.springaifunctions.model.WeatherRequest;
import com.pgedlek.springaifunctions.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

@Slf4j
public class WeatherServiceFunction implements Function<WeatherRequest, WeatherResponse> {
    static final String WEATHER_URL = "https://api.api-ninjas.com/v1/weather";

    private final String apiNinjasKey;

    public WeatherServiceFunction(String apiNinjasKey) {
        this.apiNinjasKey = apiNinjasKey;
    }

    @Override
    public WeatherResponse apply(WeatherRequest weatherRequest) {
        RestClient restClient = RestClient.builder()
                .baseUrl(WEATHER_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("X-Api-Key", apiNinjasKey);
                    httpHeaders.add("Content-Type", "application/json");
                    httpHeaders.add("Accept", "application/json");
                })
                .build();

        return restClient.get().uri(uriBuilder -> {
            log.info("Building URI for weather request with coordinates: "
                    + "lat=" + weatherRequest.latitude() + ", lon=" + weatherRequest.longitude());

            uriBuilder.queryParam("lat", weatherRequest.latitude())
                    .queryParam("lon", weatherRequest.longitude());

            return uriBuilder.build();
        }).retrieve().body(WeatherResponse.class);
    }
}

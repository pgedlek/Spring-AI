package com.pgedlek.springaifunctions.functions;

import com.pgedlek.springaifunctions.model.StockRequest;
import com.pgedlek.springaifunctions.model.StockResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

@Slf4j
public class StockServiceFunction implements Function<StockRequest, StockResponse> {
    static final String STOCK_URL = "https://api.api-ninjas.com/v1/stockprice";

    private final String apiNinjasKey;

    public StockServiceFunction(String apiNinjasKey) {
        this.apiNinjasKey = apiNinjasKey;
    }

    @Override
    public StockResponse apply(StockRequest stockRequest) {
        RestClient restClient = RestClient.builder()
                .baseUrl(STOCK_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("X-Api-Key", apiNinjasKey);
                    httpHeaders.add("Content-Type", "application/json");
                    httpHeaders.add("Accept", "application/json");
                })
                .build();

        StockResponse response = restClient.get().uri(uriBuilder -> {
            log.info("Building URI for stock price request with ticker: {}", stockRequest.ticker());

            uriBuilder.queryParam("ticker", stockRequest.ticker());

            return uriBuilder.build();
        }).retrieve().body(StockResponse.class);

        return response;
    }
}

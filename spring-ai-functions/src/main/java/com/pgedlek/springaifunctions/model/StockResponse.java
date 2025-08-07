package com.pgedlek.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record StockResponse(@JsonPropertyDescription("Ticker of stock company") String ticker,
                            @JsonPropertyDescription("Name of stock company") String name,
                            @JsonPropertyDescription("Price of stock company") Double price,
                            @JsonPropertyDescription("Exchange stock name e.g. NASDAQ") String exchange,
                            @JsonPropertyDescription("Update time in epoch") Integer updated,
                            @JsonPropertyDescription("Currency of stock price") String currency) {
}

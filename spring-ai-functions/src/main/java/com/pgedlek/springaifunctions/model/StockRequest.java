package com.pgedlek.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Stock API request using ticker")
public record StockRequest(@JsonProperty(required = true, value = "ticker")
                           @JsonPropertyDescription("Ticker of desired stock company") String ticker) {
}

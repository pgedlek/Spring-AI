package com.pgedlek.springaiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record CapitalWithInfoResponse(@JsonPropertyDescription("The capital is") String capital,
                                      @JsonPropertyDescription("The city has a population of") String population,
                                      @JsonPropertyDescription("The city is located in") String region,
                                      @JsonPropertyDescription("The primary language spoken is") String language,
                                      @JsonPropertyDescription("The currency used is")  String currency) {
}

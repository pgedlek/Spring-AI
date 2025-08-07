package com.pgedlek.springaiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record CapitalResponse(@JsonPropertyDescription("This is the city name") String answer) {
}

package com.pgedlek.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;
import java.math.BigInteger;

public record WeatherResponse(@JsonPropertyDescription("WindSpeed in KMH") BigDecimal wind_speed,
                              @JsonPropertyDescription("Direction of wind") Integer wind_degrees,
                              @JsonPropertyDescription("Current temperature in Celsius") Integer temp,
                              @JsonPropertyDescription("Current Humidity") Integer humidity,
                              @JsonPropertyDescription("Sunset timestamp") BigInteger sunset,
                              @JsonPropertyDescription("Sunrise timestamp") BigInteger sunrise,
                              @JsonPropertyDescription("Low temperature in Celsius") Integer min_temp,
                              @JsonPropertyDescription("Cloud Coverage Percentage") Integer cloud_pct,
                              @JsonPropertyDescription("Temperature in Celsius") Integer feels_like,
                              @JsonPropertyDescription("MaximumTemperature in Celsius") Integer max_temp) {
}

package com.springai.springaiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalDetailedResponse(
        @JsonPropertyDescription("This is the city name")  String capital,
        @JsonPropertyDescription("The is the city population")  String population,
        @JsonPropertyDescription("The is the city region")  String region,
        @JsonPropertyDescription("The is the city language")  String language,
        @JsonPropertyDescription("This is the city currency")  String currency) {
}

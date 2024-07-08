package com.example.adoption.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AnimalId(
        @JsonProperty("id")
        Long id
) {
}

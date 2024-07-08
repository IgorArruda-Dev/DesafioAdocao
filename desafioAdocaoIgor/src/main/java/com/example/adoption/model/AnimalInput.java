package com.example.adoption.model;

import com.example.adoption.entity.AnimalEntity;
import com.example.adoption.entity.AnimalType;
import com.example.adoption.entity.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AnimalInput(
        @JsonProperty("name")
        String name,

        @JsonProperty("description")
        String description,

        @JsonProperty("urlimage")
        String urlImage,

        @JsonProperty("category")
        @Enumerated(EnumType.STRING)
        AnimalType category,

        @JsonProperty("birthdate")
        LocalDate birthDate,

        @JsonProperty("status")
        @Enumerated(EnumType.STRING)
        Status status
) {
    public AnimalEntity ToEntity() {
        return new AnimalEntity(
                this.name,
                this.description,
                this.urlImage,
                this.category,
                this.birthDate,
                this.status
        );
    }
}


package com.example.adoption.model;

import com.example.adoption.entity.AnimalEntity;
import com.example.adoption.entity.AnimalType;
import com.example.adoption.entity.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AnimalOutput(
        @JsonProperty
        Long id,

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
        Status status
) {
    public static  AnimalOutput fromEntity(AnimalEntity entity) {
        return new AnimalOutput(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getUrlImage(),
                entity.getCategory(),
                entity.getBirthDate(),
                entity.getStatus()
        );
    }
}

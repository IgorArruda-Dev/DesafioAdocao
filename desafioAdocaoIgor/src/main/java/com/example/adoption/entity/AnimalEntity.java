package com.example.adoption.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "animals")
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String urlImage;

    @Enumerated(EnumType.STRING)
    private AnimalType category;

    @Column
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    public AnimalEntity() {
    }

    public AnimalEntity(
            String nome,
            String description,
            String urlImage,
            AnimalType category,
            LocalDate birthDate,
            Status status
    ) {
        this.name = nome;
        this.description = description;
        this.urlImage = urlImage;
        this.category = category;
        this.birthDate = birthDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public AnimalType getCategory() {
        return category;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalEntity that = (AnimalEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}



package com.example.adoption.animal;

import com.example.adoption.UnitTest;
import com.example.adoption.entity.AnimalEntity;
import com.example.adoption.entity.AnimalType;
import com.example.adoption.entity.Status;
import com.example.adoption.model.AnimalInput;
import com.example.adoption.model.AnimalOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnimalTest extends UnitTest {

    @Test
    public void givenAValidParams_whenCallNewAnimal_thenInstantiateAAnimal() {
        // assert
        final var expectedName = "Gato do Igor";
        final var expectedDescription = "Um gato laranja";
        final var expectedUrlImage = "https://meuGato.com/gato.png";
        final var expectedCategory = AnimalType.CAT;
        final var expectedStatus = Status.AVAILABLE;
        final var expectedBirthDate = LocalDate.now();

        // act
        final var actualAnimal = new AnimalEntity(
                expectedName, expectedDescription, expectedUrlImage,
                expectedCategory, expectedBirthDate, expectedStatus
        );

        Assertions.assertEquals(expectedName, actualAnimal.getName());
        Assertions.assertEquals(expectedDescription, actualAnimal.getDescription());
        Assertions.assertEquals(expectedUrlImage, actualAnimal.getUrlImage());
        Assertions.assertEquals(expectedCategory, actualAnimal.getCategory());
        Assertions.assertEquals(expectedStatus, actualAnimal.getStatus());
        Assertions.assertEquals(expectedBirthDate, actualAnimal.getBirthDate());
    }

    @Test
    public void givenAValidAnimalInput_whenCallNewEntity_thenInstantiateAAnimal() {

        final var expectedName = "Gato do Igor";
        final var expectedDescription = "Um gato laranja";
        final var expectedUrlImage = "https://meuGato.com/gato.png";
        final var expectedCategory = AnimalType.CAT;
        final var expectedStatus = Status.AVAILABLE;
        final var expectedBirthDate = LocalDate.now();

        AnimalInput expectedInput = new AnimalInput(
                expectedName, expectedDescription, expectedUrlImage,
                expectedCategory, expectedBirthDate, expectedStatus
        );

        final var actualAnimal = expectedInput.ToEntity(

                );
        Assertions.assertEquals(expectedName, actualAnimal.getName());
        Assertions.assertEquals(expectedDescription, actualAnimal.getDescription());
        Assertions.assertEquals(expectedUrlImage, actualAnimal.getUrlImage());
        Assertions.assertEquals(expectedCategory, actualAnimal.getCategory());
        Assertions.assertEquals(expectedStatus, actualAnimal.getStatus());
        Assertions.assertEquals(expectedBirthDate, actualAnimal.getBirthDate());

    }

    @Test
    public void givenAnimalEntity_whenCallFromEntity_thenReturnExpectedOutput() {
        final var expectedName = "Gato do Igor";
        final var expectedDescription = "Um gato laranja";
        final var expectedUrlImage = "https://meuGato.com/gato.png";
        final var expectedCategory = AnimalType.CAT;
        final var expectedStatus = Status.AVAILABLE;
        final var expectedBirthDate = LocalDate.now();

        AnimalEntity entity = new AnimalEntity(
                expectedName, expectedDescription, expectedUrlImage,
                expectedCategory, expectedBirthDate, expectedStatus
        );

        AnimalOutput actualOutput = AnimalOutput.fromEntity(entity);

        Assertions.assertEquals(expectedName, actualOutput.name());
        Assertions.assertEquals(expectedDescription, actualOutput.description());
        Assertions.assertEquals(expectedUrlImage, actualOutput.urlImage());
        Assertions.assertEquals(expectedCategory, actualOutput.category());
        Assertions.assertEquals(expectedStatus, actualOutput.status());
        Assertions.assertEquals(expectedBirthDate, actualOutput.birthDate());
    }

}

package com.example.adoption.controller;

import com.example.adoption.entity.Status;
import com.example.adoption.exception.DbException;
import com.example.adoption.exception.NotFoundException;
import com.example.adoption.model.AnimalId;
import com.example.adoption.model.AnimalInput;
import com.example.adoption.model.AnimalOutput;
import com.example.adoption.pagination.Pagination;
import com.example.adoption.pagination.SearchQuery;
import com.example.adoption.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/animals")
@Tag(name = "Animals")
public class AnimalController {
    private static final Logger LOG = LoggerFactory.getLogger(AnimalController.class);

    @Autowired
    private AnimalService animalService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Search all animals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successfully"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    public ResponseEntity<Pagination<AnimalOutput>> getAllAnimals(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
    ) {
        try {
            return ResponseEntity.ok().body(
                    animalService.getAllAnimals(new SearchQuery(page, perPage, search, sort, direction))
            );
        } catch (DbException e) {
            LOG.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create an animal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create successfully"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    public ResponseEntity<AnimalId> createAnimal(@RequestBody AnimalInput animalModel) {
        try {
            var animalId = animalService.createAnimal(animalModel.ToEntity());
            return ResponseEntity.status(201).body(new AnimalId(animalId));
        } catch (DbException e) {
            LOG.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}/{status}")
    @Operation(summary = "Update animal status by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update successfully"),
            @ApiResponse(responseCode = "404", description = "Animal not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @PathVariable Status status) {
        try {
            animalService.updateStatus(id, status);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            LOG.error(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (DbException e) {
            LOG.error(e.getMessage());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}

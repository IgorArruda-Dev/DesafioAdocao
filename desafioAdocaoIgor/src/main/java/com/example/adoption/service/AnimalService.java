package com.example.adoption.service;

import com.example.adoption.entity.AnimalEntity;
import com.example.adoption.entity.Status;
import com.example.adoption.exception.DbException;
import com.example.adoption.exception.NotFoundException;
import com.example.adoption.model.AnimalOutput;
import com.example.adoption.pagination.Pagination;
import com.example.adoption.pagination.SearchQuery;
import com.example.adoption.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.adoption.repository.SpecificationUtils.equal;
import static com.example.adoption.repository.SpecificationUtils.like;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public Pagination<AnimalOutput> getAllAnimals(SearchQuery query) {
        try {
            final var page = PageRequest.of(
                    query.page(),
                    query.perPage(),
                    Sort.by(Sort.Direction.fromString(query.direction()), query.sort())
            );

            final var specifications =
                    Optional.ofNullable(query.terms())
                    .filter(str -> !str.isBlank())
                    .map(this::assembleSpecification)
                    .orElse(null);

            final var pageResult =
                    animalRepository.findAll(Specification.where(specifications), page);
            return new Pagination<>(
                    pageResult.getNumber(),
                    pageResult.getSize(),
                    pageResult.getTotalElements(),
                    pageResult.map(AnimalOutput::fromEntity).toList()
            );
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    public Long createAnimal(AnimalEntity animalEntity) {
        try {
            return animalRepository.save(animalEntity).getId();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    public void updateStatus(Long id, Status status) {
        try {
            final var updated = animalRepository.updateStatus(id, status);
            if (updated == 0) {
                throw new NotFoundException(String.format("Animal not found for id: %s", id));
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    private Specification<AnimalEntity> assembleSpecification(final String str) {
        final Specification<AnimalEntity> nameLike = like("name", str);
        final Specification<AnimalEntity> categoryEqual = equal("category", str);
        final Specification<AnimalEntity> statusEqual = equal("status", str);
        return nameLike.or(categoryEqual).or(statusEqual);
    }
}


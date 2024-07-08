package com.example.adoption.repository;

import com.example.adoption.entity.AnimalEntity;
import com.example.adoption.entity.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {
    Page<AnimalEntity> findAll(Specification<AnimalEntity> whereClause, Pageable page);

    @Modifying
    @Transactional
    @Query("update AnimalEntity a set a.status = :status where a.id = :id")
    int updateStatus(@Param("id") Long id, @Param("status") Status status);
}

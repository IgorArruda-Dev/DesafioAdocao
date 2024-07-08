package com.example.adoption.repository;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtils {
    private SpecificationUtils() {}

    public static <T> Specification<T> like(final String prop, final String term) {
        return (root, query, cb) -> cb.like(cb.upper(root.get(prop)), SqlUtils.like(term));
    }

    public static <T> Specification<T> equal(final String prop, final String value) {
        return (root, query, cb) -> cb.equal(root.get(prop), value);
    }
}

package com.example.adoption.repository;

public class SqlUtils {
    private SqlUtils() {
    }

    public static String like(final String term) {
        if (term == null) return null;
        return "%" + term + "%";
    }
}

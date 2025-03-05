package com.sample.iga.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)  // Ensures unnecessary fields are ignored
@JsonInclude(JsonInclude.Include.NON_NULL)   // Excludes null fields in JSON
public class User extends PanacheEntity {

    @Column(nullable = false, unique = true)
    public String username;

    @Column(nullable = false, unique = true)
    public String email;

    @Column(name = "created_at", updatable = false)
    public LocalDateTime createdAt = LocalDateTime.now();
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("❌ JSON serialization is required! Install Jackson.");
    }
}

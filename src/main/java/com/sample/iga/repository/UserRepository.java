package com.sample.iga.repository;

import com.sample.iga.model.User;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public Uni<User> findByUsername(String username) {
        return find("username", username).firstResult();
    }

    public Uni<Boolean> deleteUser(Long id) {
        return deleteById(id);
    }
}

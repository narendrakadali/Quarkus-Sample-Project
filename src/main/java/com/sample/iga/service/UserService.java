package com.sample.iga.service;

import com.sample.iga.model.User;
import com.sample.iga.repository.UserRepository;
import io.smallrye.mutiny.Uni;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @WithSession  // 🔥 Ensure a session is opened for read operations
    public Uni<List<User>> getAllUsers() {
        return userRepository.listAll();
    }

    @WithSession
    public Uni<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @WithTransaction  // 🔥 Ensure a transaction is opened for write operations
    public Uni<User> createUser(User user) {
        return userRepository.persist(user);
    }

    @WithTransaction
    public Uni<Boolean> deleteUser(Long id) {
        return userRepository.deleteUser(id);
    }
}

package org.practise.service;

import org.practise.models.User;
import org.practise.repositories.UserRepository;

import java.io.IOException;
import java.util.UUID;

public class AppService {
    private final UserRepository repository;

    public AppService(UserRepository repository) {
        this.repository = repository;
    }

    public void signUp(String email, String password) {
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .email(email)
                .password(password)
                .build();
        repository.save(user);
    }
}

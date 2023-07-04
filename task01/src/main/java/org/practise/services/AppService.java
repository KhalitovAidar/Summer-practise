package org.practise.services;

import org.practise.models.Event;
import org.practise.models.User;
import org.practise.repositories.EventRepository;
import org.practise.repositories.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public class AppService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public AppService(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public void signUp(String email, String password) {
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .email(email)
                .password(password)
                .build();
        userRepository.save(user);
    }

    public List<Event> getAllEventsByUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        return eventRepository.findAllByMembersContains(user.orElseThrow(() -> {
            throw new NoSuchElementException("Пользователя с таким email нет");
        }));
    }
}

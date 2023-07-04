package org.practise.repositories;

import org.practise.models.Event;
import org.practise.models.User;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends CRUDRepository<Event> {
    Optional<Event> findByName(String nameEvent);
    Optional<Event> findById(String id);
    void saveUserToEvent(User user, Event event);
    List<Event> findAllByMembersContains(User user);
}
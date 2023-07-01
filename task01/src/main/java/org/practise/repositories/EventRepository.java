package org.practise.repositories;

import org.practise.models.Event;
import org.practise.models.User;

import java.util.List;

public interface EventRepository extends CRUDRepository<Event> {
    Event findByName(String nameEvent);
    Event findById(String id);
    void saveUserToEvent(User user, Event event);
    List<Event> findAllByMembersContains(User user);
}
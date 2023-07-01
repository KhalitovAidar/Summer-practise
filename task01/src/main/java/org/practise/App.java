package org.practise;

import org.practise.models.Event;
import org.practise.models.User;
import org.practise.repositories.EventRepository;
import org.practise.repositories.UserRepository;
import org.practise.repositories.impl.EventRepositoryFileImpl;
import org.practise.repositories.impl.UserRepositoryFileImpl;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserRepository userRepository = new UserRepositoryFileImpl("users.txt");
        EventRepository eventRepository = new EventRepositoryFileImpl("events.txt", "events_users");

        User user = userRepository.findByEmail("khalitovaidar2404@gmail.com");
        System.out.println("Пользователь найден: " + user);

        Event event = eventRepository.findByName("Практика по Golang");
        System.out.println("Событие найдено: " + event);

        List<Event> events = eventRepository.findAllByMembersContains(user);
        System.out.println(events.toString());
    }
}

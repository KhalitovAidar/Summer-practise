package org.practise;

import org.practise.models.Event;
import org.practise.models.User;
import org.practise.repositories.EventRepository;
import org.practise.repositories.UserRepository;
import org.practise.repositories.impl.EventRepositoryFileImpl;
import org.practise.repositories.impl.UserRepositoryFileImpl;
import org.practise.services.AppService;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserRepository userRepository = new UserRepositoryFileImpl("users.txt");
        EventRepository eventRepository = new EventRepositoryFileImpl("events.txt", "events_users.txt");
        AppService appService = new AppService(userRepository, eventRepository);

        User user = userRepository.findByEmail("khalitovaidar2404@gmail.com")
                .orElseThrow(() -> {
                    throw new NoSuchElementException("нет такого пользователя");
                });

        List<Event> events = appService.getAllEventsByUser(user.getEmail());
        System.out.println(events.toString());
    }
}

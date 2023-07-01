package org.practise.repositories.impl;

import org.practise.models.Event;
import org.practise.models.User;
import org.practise.repositories.EventRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventRepositoryFileImpl implements EventRepository {
    private final String fileName;
    private final String eventsAndUsersFileName;
    public EventRepositoryFileImpl(String fileName, String eventsAndUsersFileName) {
        this.fileName = fileName;
        this.eventsAndUsersFileName = eventsAndUsersFileName;
    }

    @Override
    public void save(Event model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(model.getId() + "|" + model.getName() + "|" + model.getDate());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Event findByName(String nameEvent) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            List<String> eventData;

            while((line = reader.readLine()) != null) {
                eventData = List.of(line.split("\\|"));

                if (eventData.get(1).equals(nameEvent)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    return Event.builder()
                            .id(eventData.get(0))
                            .name(eventData.get(1))
                            .date(LocalDate.parse(eventData.get(2), formatter))
                            .build();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("Событие не найдено");
    }

    @Override
    public Event findById(String id) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            List<String> eventData;

            while((line = reader.readLine()) != null) {
                eventData = List.of(line.split("\\|"));

                if (eventData.get(0).equals(id)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    return Event.builder()
                            .id(eventData.get(0))
                            .name(eventData.get(1))
                            .date(LocalDate.parse(eventData.get(2), formatter))
                            .build();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("Событие не найдено");
    }

    @Override
    public void saveUserToEvent(User user, Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventsAndUsersFileName, true))){
            writer.write(user.getId() + "|" + event.getId());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Event> findAllByMembersContains(User user) {
        String id = user.getId();

        try(BufferedReader reader = new BufferedReader(new FileReader(eventsAndUsersFileName))) {
            String line;
            List<String> eventAndUserIds;
            List<Event> allEvents = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                eventAndUserIds = List.of(line.split("\\|"));

                if (eventAndUserIds.get(0).equals(id)) {
                    Event currentEvent = findById(eventAndUserIds.get(1));
                    allEvents.add(currentEvent);
                }
            }
            return allEvents;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

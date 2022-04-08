package com.restapi.impl;

import com.restapi.api.EventService;
import com.restapi.dto.Event;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Override
    public void createEvent(Event event) {

    }

    @Override
    public void updateEvent(Event event) {

    }

    @Override
    public Event getEvent(String id) {
        return new Event(
                "1234",
                "Title",
                "NewYork",
                "Speaker",
                "Persisten",
                new Date()
        );
    }

    @Override
    public void deleteEvent(String id) {

    }

    @Override
    public List<Event> getAllEvents() {
        return null;
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return null;
    }
}

package com.restapi.impl;

import com.restapi.EventRepository;
import com.restapi.api.EventService;
import com.restapi.dto.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event getEvent(Integer id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return eventRepository.findByTitle(title);
    }
}

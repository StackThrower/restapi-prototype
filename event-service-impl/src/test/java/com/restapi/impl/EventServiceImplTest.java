package com.restapi.impl;

import com.restapi.EventRepository;
import com.restapi.api.EventService;
import com.restapi.dto.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    EventService eventService;

    @Mock
    EventRepository eventRepository;

    @Mock
    Event event;


    @BeforeEach
    void setUp() {

        eventService = new EventServiceImpl(eventRepository);
    }

    @Test
    void createEvent() {

        eventService.createEvent(event);

        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void updateEvent() {
        eventService.updateEvent(event);

        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void getEvent() {

        when(eventRepository.findById(1)).thenReturn(Optional.of(new Event(1, "", "", "", "", new Date())));

        Event testObj = eventService.getEvent(1);

        verify(eventRepository, times(1)).findById(1);

        assertEquals("equal ids", testObj.getId(), 1);
    }

    @Test
    void deleteEvent() {
        eventService.deleteEvent(event);

        verify(eventRepository, times(1)).delete(event);
    }

    @Test
    void getAllEvents() {
        eventService.getAllEvents();

        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void getAllEventsByTitle() {
        eventService.getAllEventsByTitle("test");

        verify(eventRepository, times(1)).findByTitle("test");

    }
}
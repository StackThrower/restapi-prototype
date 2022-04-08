package com.restapi.api;

import com.restapi.dto.Event;

import java.util.List;

public interface EventService {

    /**
     * Creates new event
     * @param event
     */
    void createEvent(Event event);

    /**
     * Updates existing event
     * @param event
     */
    void updateEvent(com.restapi.dto.Event event);

    /**
     * Get event by id
     * @param id
     * @return
     */
    Event getEvent(Integer id);

    /**
     * Remove event from the db
     * @param event
     */
    void deleteEvent(Event event);

    /**
     * Get all events
     * @return
     */
    Iterable<Event> getAllEvents();

    /**
     * Get event by title
     * @param title
     * @return
     */
    List<Event> getAllEventsByTitle(String title);

}

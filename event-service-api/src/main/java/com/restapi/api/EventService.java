package com.restapi.api;

import com.restapi.dto.Event;

import java.util.List;

public interface EventService {

    /**
     * Creates new event.
     *
     * @param event new event to be added
     * @return new event with valid id
     */
    Event createEvent(Event event);

    /**
     * Updates existing event.
     *
     * @param event updated event in order to save in persistent layer
     * @return the updated event
     */
    Event updateEvent(Event event);

    /**
     * Get event by id.
     *
     * @param id unique identifier
     * @return event object or null if not found
     */
    Event getEvent(Integer id);

    /**
     * Remove event from the db.
     * TODO explain the behavior if the event object is invalid
     * @param event object with valid id field
     */
    void deleteEvent(Event event);

    /**
     * Get all events or empty list
     *
     * @return the list of event objects
     */
    List<Event> getAllEvents();

    /**
     * Get event by title
     *
     * @param title the value for the search
     * @return the list of events of empty object
     */
    List<Event> getAllEventsByTitle(String title);

}

package com.restapi.rest;

import com.restapi.api.EventService;
import com.restapi.dto.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    private EventService eventService;

    @Autowired
    public MainController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Create new event
     * @param payload
     * @return
     */
    @PostMapping("/events")
    public ResponseEntity<EventApi> createEvent(@RequestBody EventApi payload) {

        Event event = payload.getEvent();

        event = eventService.createEvent(event);

        return new ResponseEntity<>(EventApi.builder()
                .id(event.getId())
                .title(event.getTitle())
                .eventType(event.getEventType())
                .place(event.getPlace())
                .speaker(event.getSpeaker())
                .dateTime(event.getDateTime())
                .build(),
                HttpStatus.CREATED);
    }

    /**
     * Get all events from the storage.
     * @return
     */
    @GetMapping("/events")
    public ResponseEntity<List<EventApi>> getEventList() {

        List<EventApi> events = eventService.getAllEvents()
                .stream().map(event -> new EventApi(event.getId(),
                        event.getTitle(),
                        event.getPlace(),
                        event.getSpeaker(),
                        event.getEventType(),
                        event.getDateTime()))
                .collect(Collectors.toList());

        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(events,
                HttpStatus.OK);
    }

    /**
     * Get one event by id
     * @param id
     * @return
     */
    @GetMapping("/events/{id}")
    public ResponseEntity<EventApi> getById(@PathVariable Integer id) {

        Event event = eventService.getEvent(id);

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(EventApi.builder()
                .id(event.getId())
                .title(event.getTitle())
                .eventType(event.getEventType())
                .place(event.getPlace())
                .speaker(event.getSpeaker())
                .dateTime(event.getDateTime())
                .build(),
                HttpStatus.OK);
    }

    /**
     * Update event by id
     * @param id
     * @return
     */
    @PutMapping("/events/{id}")
    public ResponseEntity<EventApi> updateEvent(@PathVariable Integer id, @RequestBody EventApi payload) {

        Event event = payload.getEvent();
        event = eventService.updateEvent(event);


        return new ResponseEntity<>(EventApi.builder()
                .id(event.getId())
                .title(event.getTitle())
                .eventType(event.getEventType())
                .place(event.getPlace())
                .speaker(event.getSpeaker())
                .dateTime(event.getDateTime())
                .build(),
                HttpStatus.CREATED);
    }

    /**
     * Delete event by id
     * @param id
     * @return
     */
    @DeleteMapping("/events/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable Integer id) {
        Event event = eventService.getEvent(id);
        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        eventService.deleteEvent(event);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

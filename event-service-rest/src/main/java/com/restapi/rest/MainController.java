package com.restapi.rest;

import com.restapi.api.EventService;
import com.restapi.dto.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    private EventService eventService;

    @Autowired
    public MainController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Create new event.
     *
     * @param payload JSON input data
     * @return the event object with valid id field
     */
    @PostMapping("/events")
    public ResponseEntity<EntityModel<EventApiResponse>> createEvent(@RequestBody EventApiRequest payload) {

        Event event = payload.getEvent();

        event = eventService.createEvent(event);

        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(MainController.class).createEvent(new EventApiRequest())).withSelfRel());
        links.add(linkTo(methodOn(MainController.class).getById(event.getId())).withRel("get"));
        links.add(linkTo(methodOn(MainController.class).updateEvent(event.getId(), new EventApiRequest())).withRel("update"));
        links.add(linkTo(methodOn(MainController.class).deleteEvent(event.getId())).withRel("delete"));

        EntityModel<EventApiResponse> entity = EntityModel.of(EventApiResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .eventType(event.getEventType())
                .place(event.getPlace())
                .speaker(event.getSpeaker())
                .dateTime(event.getDateTime())
                .build(), links);

        return new ResponseEntity<>(entity,
                HttpStatus.CREATED);
    }

    /**
     * Get all events from the storage.
     *
     * @return the list of events or 404 HTTP code
     */
    @GetMapping("/events")
    public ResponseEntity<List<EntityModel<EventApiResponse>>> getEventList() {

        List<EntityModel<EventApiResponse>> events = eventService.getAllEvents()
                .stream().map(event -> {

                    List<Link> links = new ArrayList<>();
                    links.add(linkTo(methodOn(MainController.class).getById(event.getId())).withRel("get"));
                    links.add(linkTo(methodOn(MainController.class).updateEvent(event.getId(), new EventApiRequest())).withRel("update"));
                    links.add(linkTo(methodOn(MainController.class).deleteEvent(event.getId())).withRel("delete"));

                    return EntityModel.of(EventApiResponse.builder()
                            .id(event.getId())
                            .title(event.getTitle())
                            .eventType(event.getEventType())
                            .place(event.getPlace())
                            .speaker(event.getSpeaker())
                            .dateTime(event.getDateTime())
                            .build(), links);
                })
                .collect(Collectors.toList());

        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(events,
                HttpStatus.OK);
    }

    /**
     * Get one event by id.
     *
     * @param id unique identifier
     * @return the event object or 404 HTTP code
     */
    @GetMapping("/events/{id}")
    public ResponseEntity<EntityModel<EventApiResponse>> getById(@PathVariable Integer id) {

        Event event = eventService.getEvent(id);

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(MainController.class).getById(id)).withSelfRel());
        links.add(linkTo(methodOn(MainController.class).updateEvent(id, new EventApiRequest())).withRel("update"));
        links.add(linkTo(methodOn(MainController.class).deleteEvent(id)).withRel("delete"));

        EntityModel<EventApiResponse> result = EntityModel.of(EventApiResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .eventType(event.getEventType())
                .place(event.getPlace())
                .speaker(event.getSpeaker())
                .dateTime(event.getDateTime())
                .build(), links);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Update event by id.
     *
     * @param id unique identifier of event
     * @return the updated event object
     */
    @PutMapping("/events/{id}")
    public ResponseEntity<EntityModel<EventApiResponse>> updateEvent(@PathVariable Integer id, @RequestBody EventApiRequest payload) {

        Event event = payload.getEvent();
        event = eventService.updateEvent(event);

        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(MainController.class).updateEvent(id, new EventApiRequest())).withSelfRel());
        links.add(linkTo(methodOn(MainController.class).getById(id)).withRel("get"));
        links.add(linkTo(methodOn(MainController.class).deleteEvent(id)).withRel("delete"));

        EntityModel<EventApiResponse> entity = EntityModel.of(EventApiResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .eventType(event.getEventType())
                .place(event.getPlace())
                .speaker(event.getSpeaker())
                .dateTime(event.getDateTime())
                .build(), links);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    /**
     * Delete event by id
     *
     * @param id unique identifier
     * @return HTTP code depends of the internal logic
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

package com.restapi.rest;

import com.restapi.api.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private EventService eventService;

    @Autowired
    public MainController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/test")
    String getTest() {
        return "This is a test REST response, event:" + eventService.getEvent("1234").toString();
    }
}

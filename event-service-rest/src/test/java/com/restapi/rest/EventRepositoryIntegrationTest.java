package com.restapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.restapi.EventRepository;
import com.restapi.dto.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class EventRepositoryIntegrationTest {

    @MockBean
    EventRepository eventRepository;

    final String ONE_EVENT = "{" +
            "\"id\": -1," +
            "\"title\": \"Test Event\"," +
            "\"place\": \"London\"," +
            "\"speaker\": \"George Bush\"," +
            "\"eventType\": \"talk\"," +
            "\"dateTime\": \"2022-04-10T17:45:45.0000\"" +
            "}";

    final Event EVENT_FROM_DB = new Event(1, "", "", "", "", new Date());

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void createEvent() throws Exception {

        when(eventRepository.save(any(Event.class)))
                .thenReturn(EVENT_FROM_DB);

        MvcResult result = mvc.perform(post("/api/v1/events")
                        .contentType("application/json")
                        .content(ONE_EVENT)
                )
                .andExpectAll(
                        status().is2xxSuccessful()
                ).andReturn();

        String response = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.readValue(response, ObjectNode.class);

        int id = -1;

        if (node.has("id"))
            id = node.get("id").asInt();

        // verity the returned id from the mocked repository
        assertEquals("", id, 1);

        verify(eventRepository, times(1)).save(any(Event.class));
    }




}

package com.restapi.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MainControllerTest {

    @Autowired
    WebApplicationContext context;

    final String ONE_EVENT = "{" +
            "\"id\": -1," +
            "\"title\": \"Test Event\"," +
            "\"place\": \"London\"," +
            "\"speaker\": \"George Bush\"," +
            "\"eventType\": \"talk\"," +
            "\"dateTime\": \"2022-04-10T17:45:45.0000\"" +
            "}";

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void eventList200() throws Exception {
        mvc.perform(get("/api/v1/events"))
                .andExpectAll(
                        status().is2xxSuccessful()
                );
    }

    @Test
    void createEvent() throws Exception {

        mvc.perform(post("/api/v1/events")
                        .contentType("application/json")
                        .content(ONE_EVENT)
                )
                .andExpectAll(
                        status().is2xxSuccessful()
                );
    }

}
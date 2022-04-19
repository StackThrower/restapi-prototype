package com.restapi.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventApiResponse extends RepresentationModel<EventApiResponse> {

    private Integer id;

    private String title;

    private String place;

    private String speaker;

    private String eventType;

    private Date dateTime;
}

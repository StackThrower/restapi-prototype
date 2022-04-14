package com.restapi;

import com.restapi.dto.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventApi {

    private Integer id;

    private String title;

    private String place;

    private String speaker;

    private String eventType;

    private Date dateTime;

    public Event getEvent() {
        return new Event(id, title, place, speaker,
                eventType, dateTime);
    }
}

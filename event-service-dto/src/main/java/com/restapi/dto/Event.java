package com.restapi.dto;

import java.util.Date;

public class Event {
    String id;
    String title;
    String place;
    String speaker;
    String eventType;
    Date dateTime;

    public Event(String id, String title, String place, String speaker, String eventType, Date dateTime) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.speaker = speaker;
        this.eventType = eventType;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", place='" + place + '\'' +
                ", speaker='" + speaker + '\'' +
                ", eventType='" + eventType + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}

package com.restapi.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_event", schema = "public")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name="title", nullable = false, length = 200)
    String title;

    @Column(name="place", nullable = false, length = 200)
    String place;

    @Column(name="speaker", nullable = false, length = 200)
    String speaker;

    @Column(name="eventType", nullable = false, length = 200)
    String eventType;

    @Column(name="dateTime", nullable = false)
    Date dateTime;

    public Event() {

    }

    public Event(Integer id, String title, String place, String speaker, String eventType, Date dateTime) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.speaker = speaker;
        this.eventType = eventType;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String getTitle() {
        return title;
    }

    public String getEventType() {
        return eventType;
    }

    public Date getDateTime() {
        return dateTime;
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

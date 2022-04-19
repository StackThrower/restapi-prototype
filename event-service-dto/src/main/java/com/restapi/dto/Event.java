package com.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_event", schema = "public")
public class Event {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Getter
    @Column(name="title", nullable = false, length = 200)
    String title;

    @Getter
    @Column(name="place", nullable = false, length = 200)
    String place;

    @Getter
    @Column(name="speaker", nullable = false, length = 200)
    String speaker;

    @Getter
    @Column(name="eventType", nullable = false, length = 200)
    String eventType;

    @Getter
    @Column(name="dateTime", nullable = false)
    Date dateTime;

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

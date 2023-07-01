package org.practise.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data //toString, getters, setters, equals, hashcode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    private String id;
    private LocalDate date;
    private String name;
}

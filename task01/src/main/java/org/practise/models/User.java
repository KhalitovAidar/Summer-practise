package org.practise.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data //toString, getters, setters, equals, hashcode
@AllArgsConstructor
@NoArgsConstructor
@Builder // функционал для удобного создания объектов
public class User {
    private String id;
    private String email;
    private String password;
}

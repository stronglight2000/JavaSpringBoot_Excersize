package com.example.demo4.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class People {
    int id;
    String fullname;
    String job;
    String gender;
    String city;
    int salary;
    LocalDate birthday;

}

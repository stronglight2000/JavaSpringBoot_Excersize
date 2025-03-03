package com.example.demo1.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.ErrorResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)



public class Book {
    private String id;
    private String title;
    private String author;
    private int year;


}

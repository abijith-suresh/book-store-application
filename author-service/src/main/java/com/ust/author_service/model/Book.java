package com.ust.author_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String title;
    private String isbn;
    private String description;
    private String genre;
    private LocalDate publishedDate;
    private double price;

}

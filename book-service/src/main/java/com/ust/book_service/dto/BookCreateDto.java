package com.ust.book_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookCreateDto {
    private String title;
    private String isbn;
    private String description;
    private String genre;
    private LocalDate publishedDate;
    private double price;

    private int authorId;
}

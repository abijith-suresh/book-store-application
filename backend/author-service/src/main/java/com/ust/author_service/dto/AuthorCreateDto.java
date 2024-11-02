package com.ust.author_service.dto;

import lombok.Data;

@Data
public class AuthorCreateDto {

    private String name;
    private String biography;
    private String nationality;
}

package com.ust.author_service.controller;

import com.ust.author_service.client.BookClient;
import com.ust.author_service.dto.AuthorCreateDto;
import com.ust.author_service.model.Author;
import com.ust.author_service.model.Book;
import com.ust.author_service.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookClient bookClient;

    @PostMapping("/create")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorCreateDto authorCreateDto){
        return ResponseEntity.ok(authorService.createAuthor(authorCreateDto));
    }

    @GetMapping
    public ResponseEntity<List<Author>> findAll(){
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findAuthorById(@PathVariable int id){
        return ResponseEntity.ok(authorService.findAuthorById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable int id){
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<List<Book>> findBooksByAuthorId(@PathVariable int id){
        return ResponseEntity.ok(bookClient.getBooksByAuthorId(id));
    }
}

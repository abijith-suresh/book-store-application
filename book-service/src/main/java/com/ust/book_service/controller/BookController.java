package com.ust.book_service.controller;

import com.ust.book_service.dto.BookCreateDto;
import com.ust.book_service.model.Book;
import com.ust.book_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody BookCreateDto bookCreateDto){
        return ResponseEntity.ok(bookService.createBook(bookCreateDto));
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll(){
        return ResponseEntity.ok(bookService.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable int id){
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable int id){
        return ResponseEntity.ok(bookService.findBookById(id));
    }
}

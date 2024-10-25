package com.ust.author_service.client;

import com.ust.author_service.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "book-service")
public interface BookClient {
    @GetMapping("/api/books/author/{id}")
    List<Book> getBooksByAuthorId(@PathVariable("id") int id);
}

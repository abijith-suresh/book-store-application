package com.ust.book_service.service;

import com.ust.book_service.dto.BookCreateDto;
import com.ust.book_service.exception.BookNotFoundException;
import com.ust.book_service.model.Book;
import com.ust.book_service.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    private Book dtoToModel(BookCreateDto bookCreateDto){
        Book book = new Book();

        book.setTitle(bookCreateDto.getTitle());
        book.setAuthorId(bookCreateDto.getAuthorId());
        book.setIsbn(bookCreateDto.getIsbn());
        book.setDescription(bookCreateDto.getDescription());
        book.setGenre(bookCreateDto.getGenre());
        book.setPublishedDate(bookCreateDto.getPublishedDate());
        book.setPrice(bookCreateDto.getPrice());

        return book;
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book createBook(BookCreateDto bookCreateDto){
        Book book = dtoToModel(bookCreateDto);
        return bookRepo.save(book);
    }

    public void deleteBookById(int id){
        bookRepo.deleteById(id);
    }

    public Book findBookById(int id){
        return bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("There is no Book of the given Id"));
    }

}

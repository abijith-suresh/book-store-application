package com.ust.author_service.service;

import com.ust.author_service.dto.AuthorCreateDto;
import com.ust.author_service.exception.AuthorNotFoundException;
import com.ust.author_service.model.Author;
import com.ust.author_service.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepo authorRepo;

    private Author dtoToModel(AuthorCreateDto authorCreateDto){
        Author author = new Author();

        author.setName(authorCreateDto.getName());
        author.setBiography(authorCreateDto.getBiography());
        author.setNationality(authorCreateDto.getNationality());

        return author;
    }

    public Author createAuthor(AuthorCreateDto authorCreateDto){
        Author author = dtoToModel(authorCreateDto);
        return authorRepo.save(author);
    }

    public List<Author> findAll(){
        return authorRepo.findAll();
    }

    public void deleteAuthorById(int id){
        authorRepo.deleteById(id);
    }

    public Author findAuthorById(int id){
        return authorRepo.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("There is no Author of the given Id"));
    }
}

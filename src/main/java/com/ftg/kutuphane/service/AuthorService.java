package com.ftg.kutuphane.service;

import com.ftg.kutuphane.entitiy.Author;
import com.ftg.kutuphane.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("authorService")
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public long count(){
        return authorRepository.count();
    }

    public List<Author> findTop5(){
        return authorRepository.findTop5ByOrderByIdDesc();
    }
}

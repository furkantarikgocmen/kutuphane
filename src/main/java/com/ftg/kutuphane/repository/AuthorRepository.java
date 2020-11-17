package com.ftg.kutuphane.repository;

import com.ftg.kutuphane.entitiy.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findTop5ByOrderByIdDesc();

    Author findById(UUID id);
}

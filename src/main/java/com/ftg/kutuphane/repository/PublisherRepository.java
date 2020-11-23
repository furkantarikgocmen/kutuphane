package com.ftg.kutuphane.repository;

import com.ftg.kutuphane.entitiy.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    List<Publisher> findTop5ByOrderByIdDesc();

    Publisher findById(UUID id);
}

package com.ftg.kutuphane.service;

import com.ftg.kutuphane.entitiy.Publisher;
import com.ftg.kutuphane.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("publisherService")
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public long count() {
        return publisherRepository.count();
    }

    public List<Publisher> findTop5() {
        return publisherRepository.findTop5ByOrderByIdDesc();
    }
}

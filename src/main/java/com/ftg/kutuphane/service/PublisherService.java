package com.ftg.kutuphane.service;

import com.ftg.kutuphane.entitiy.Publisher;
import com.ftg.kutuphane.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> findAll(){
        return publisherRepository.findAll();
    }
}

package com.ftg.kutuphane.controller;

import com.ftg.kutuphane.entitiy.Publisher;
import com.ftg.kutuphane.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping(value = "/publisher")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView panel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("publishers", publisherService.findAll());
        modelAndView.setViewName("publisher/panelPublisher");
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newPublisherPage() {
        ModelAndView modelAndView = new ModelAndView();
        Publisher publisher = new Publisher();
        modelAndView.addObject("publisher", publisher);
        modelAndView.addObject("publishers", publisherService.findAll());
        modelAndView.setViewName("publisher/newPublisher");
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView newPublisher(@ModelAttribute(value = "publisher") Publisher publisher) {
        ModelAndView modelAndView = new ModelAndView();
        Publisher newPublisher = new Publisher();
        modelAndView.addObject("state", publisherService.save(publisher));
        modelAndView.addObject("publisher", newPublisher);
        modelAndView.setViewName("publisher/newPublisher");
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView updatePublisherPage(@PathVariable("id") UUID id) {
        ModelAndView modelAndView = new ModelAndView();
        Publisher publisher = publisherService.findById(id);
        modelAndView.addObject("publisher", publisher);
        modelAndView.setViewName("publisher/updatePublisher");
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView updatePublisher(@PathVariable("id") UUID id, @ModelAttribute(value = "publisher") Publisher publisher) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("state", publisherService.update(publisher));
        modelAndView.addObject("publisher", publisherService.findById(id));
        modelAndView.setViewName("publisher/updatePublisher");
        return modelAndView;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletePublisher(@PathVariable("id") UUID id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("state", publisherService.delete(publisherService.findById(id)));
        modelAndView.addObject("publisher", publisherService.findAll());
        modelAndView.addObject("publishers", publisherService.findAll());
        modelAndView.setViewName("publisher/panelPublisher");
        return modelAndView;
    }
}

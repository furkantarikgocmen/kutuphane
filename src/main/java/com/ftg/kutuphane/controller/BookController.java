package com.ftg.kutuphane.controller;

import com.ftg.kutuphane.entitiy.Book;
import com.ftg.kutuphane.service.AuthorService;
import com.ftg.kutuphane.service.BookService;
import com.ftg.kutuphane.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping(value = "/book")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView panel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("books", bookService.findAll());
        modelAndView.setViewName("book/panelBook");
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newBookPage() {
        ModelAndView modelAndView = new ModelAndView();
        Book book = new Book();
        modelAndView.addObject("book", book);
        modelAndView.addObject("authors", authorService.findAll());
        modelAndView.addObject("publishers", publisherService.findAll());
        modelAndView.setViewName("book/newBook");
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView newBook(@ModelAttribute(value = "book") Book book){
        ModelAndView modelAndView = new ModelAndView();
        Book newBook = new Book();
        modelAndView.addObject("state", bookService.save(book));
        modelAndView.addObject("book", newBook);
        modelAndView.addObject("authors", authorService.findAll());
        modelAndView.addObject("publishers", publisherService.findAll());
        modelAndView.setViewName("book/newBook");
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView updateBookPage(@PathVariable("id") UUID id) {
        ModelAndView modelAndView = new ModelAndView();
        Book book = bookService.findById(id);
        modelAndView.addObject("book", book);
        modelAndView.addObject("publishers", publisherService.findAll());
        modelAndView.addObject("authors", authorService.findAll());
        modelAndView.setViewName("book/updateBook");
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView updateBook(@PathVariable("id") UUID id, @ModelAttribute(value = "book") Book book){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("state",bookService.update(book));
        modelAndView.addObject("book",bookService.findById(id));
        modelAndView.addObject("publishers", publisherService.findAll());
        modelAndView.addObject("authors", authorService.findAll());
        modelAndView.setViewName("book/updateBook");
        return modelAndView;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteBook(@PathVariable("id") UUID id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("state", bookService.delete(bookService.findById(id)));
        modelAndView.addObject("books", bookService.findAll());
        modelAndView.setViewName("book/panelBook");
        return modelAndView;
    }
}

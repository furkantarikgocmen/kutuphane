package com.ftg.kutuphane.controller;

import com.ftg.kutuphane.entitiy.Author;
import com.ftg.kutuphane.service.AccountService;
import com.ftg.kutuphane.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping(value = "/author")
public class AuthorController {
    private final AuthorService authorService;
    private final AccountService accountService;

    public AuthorController(AuthorService authorService, AccountService accountService) {
        this.authorService = authorService;
        this.accountService = accountService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView panel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("authors", authorService.findAll());
        modelAndView.addObject("account", accountService.getActiveAccount());
        modelAndView.setViewName("author/panelAuthor");
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newAuthorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("account", accountService.getActiveAccount());
        Author author = new Author();
        modelAndView.addObject("author", author);
        modelAndView.setViewName("author/newAuthor");
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView newAuthor(@ModelAttribute(value = "author") Author author) {
        ModelAndView modelAndView = new ModelAndView();
        Author newAuthor = new Author();
        modelAndView.addObject("account", accountService.getActiveAccount());
        modelAndView.addObject("state", authorService.save(author));
        modelAndView.addObject("author", newAuthor);
        modelAndView.setViewName("author/newAuthor");
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView updateAuthorPage(@PathVariable("id") UUID id) {
        ModelAndView modelAndView = new ModelAndView();
        Author author = authorService.findById(id);
        modelAndView.addObject("account", accountService.getActiveAccount());
        modelAndView.addObject("author", author);
        modelAndView.setViewName("author/updateAuthor");
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView updateAuthor(@PathVariable("id") UUID id, @ModelAttribute(value = "author") Author author) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("state", authorService.update(author));
        modelAndView.addObject("account", accountService.getActiveAccount());
        modelAndView.addObject("author", authorService.findById(id));
        modelAndView.setViewName("author/updateAuthor");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteAuthor(@PathVariable("id") UUID id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("state", authorService.delete(authorService.findById(id)));
        modelAndView.addObject("account", accountService.getActiveAccount());
        modelAndView.addObject("authors", authorService.findAll());
        modelAndView.setViewName("author/panelAuthor");
        return modelAndView;
    }
}

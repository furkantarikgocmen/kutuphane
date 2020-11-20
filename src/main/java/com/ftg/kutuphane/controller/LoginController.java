package com.ftg.kutuphane.controller;

import com.ftg.kutuphane.entitiy.Account;
import com.ftg.kutuphane.entitiy.BackState;
import com.ftg.kutuphane.enums.StateCode;
import com.ftg.kutuphane.service.AccountService;
import com.ftg.kutuphane.service.AuthorService;
import com.ftg.kutuphane.service.BookService;
import com.ftg.kutuphane.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final AccountService accountService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final PublisherService publisherService;

    public LoginController(AccountService accountService, AuthorService authorService, BookService bookService, PublisherService publisherService) {
        this.accountService = accountService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.publisherService = publisherService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView rootPage() {
        ModelAndView modelAndView = new ModelAndView();

        if (accountService.isAuthenticated()) {
            modelAndView.setViewName("redirect:/panel");
        } else {
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView();

        if (accountService.isAuthenticated()) {
            modelAndView.setViewName("redirect:/panel");
        } else {
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

    public ModelAndView getPanelObjects(ModelAndView modelAndView) {
        modelAndView.addObject("account", accountService.getActiveAccount());
        modelAndView.addObject("totalBook", bookService.count());
        modelAndView.addObject("totalPublisher", publisherService.count());
        modelAndView.addObject("totalAuthor", authorService.count());
        modelAndView.addObject("totalUser", accountService.count());
        modelAndView.addObject("top5Book", bookService.findTop5());
        modelAndView.addObject("top5Publisher", publisherService.findTop5());
        modelAndView.addObject("top5Author", authorService.findTop5());
        return modelAndView;
    }

    @RequestMapping(value = "/panel")
    public ModelAndView panel() { //Principal
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = getPanelObjects(modelAndView);
        modelAndView.setViewName("panel");
        return modelAndView;
    }

    @RequestMapping(value = "/access-denied")
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView();
        BackState backState = new BackState();
        backState.setMessage("Yetkisiz Erişim Reddedildi!");
        if (accountService.isAuthenticated()) {
            modelAndView = getPanelObjects(modelAndView);
            modelAndView.setViewName("panel");
            backState.setStateCode(StateCode.WARNING);
        } else {
            modelAndView.setViewName("index");
            backState.setStateCode(StateCode.ERROR);
        }
        modelAndView.addObject("state", backState);
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        ModelAndView modelAndView = new ModelAndView();

        if (accountService.isAuthenticated()) {
            modelAndView.setViewName("redirect:/panel");
        } else {
            modelAndView.addObject("account", new Account());
            modelAndView.setViewName("register");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid @ModelAttribute(value = "account") Account account) {
        ModelAndView modelAndView = new ModelAndView();
        BackState backState;
        backState = accountService.newAccount(account);
        if (backState.getStateCode() != StateCode.SUCCESS.getStateCode()) {
            modelAndView.setViewName("register");
            modelAndView.addObject(account);
        } else
            modelAndView.setViewName("index");
        modelAndView.addObject("state", backState);
        return modelAndView;
    }
}

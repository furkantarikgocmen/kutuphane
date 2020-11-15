package com.ftg.kutuphane.controller;

import com.ftg.kutuphane.entitiy.Account;
import com.ftg.kutuphane.service.AccountService;
import com.ftg.kutuphane.service.AuthorService;
import com.ftg.kutuphane.service.BookService;
import com.ftg.kutuphane.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView indexPage(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)){
            modelAndView.setViewName("redirect:/panel");
        }else{
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/panel")
    public ModelAndView panel(){ //Principal
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //boolean isAdmin = (Arrays.toString(authentication.getAuthorities().toArray()).contains("ADMIN"));
        //boolean isModerator = (Arrays.toString(authentication.getAuthorities().toArray()).contains("MODERATOR"));
        //boolean isUser = (Arrays.toString(authentication.getAuthorities().toArray()).contains("USER"));
        Account account = accountService.findAccountByUserName(authentication.getName());

        modelAndView.addObject("account", account);
        modelAndView.addObject("totalBook", bookService.count());
        modelAndView.addObject("totalPublisher", publisherService.count());
        modelAndView.addObject("totalAuthor", authorService.count());
        modelAndView.addObject("totalUser", accountService.count());
        modelAndView.addObject("top5Book", bookService.findTop5());
        modelAndView.addObject("top5Publisher", publisherService.findTop5());
        modelAndView.addObject("top5Author", authorService.findTop5());
        modelAndView.setViewName("panel");
        return modelAndView;
    }
}

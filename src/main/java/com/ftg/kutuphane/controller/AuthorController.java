package com.ftg.kutuphane.controller;

import com.ftg.kutuphane.entitiy.Account;
import com.ftg.kutuphane.entitiy.Author;
import com.ftg.kutuphane.service.AccountService;
import com.ftg.kutuphane.service.AuthorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/author")
public class AuthorController {
    private final AuthorService authorService;
    private final AccountService accountService;

    public AuthorController(AuthorService authorService, AccountService accountService){
        this.authorService = authorService;
        this.accountService = accountService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView panel(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.findAccountByUserName(authentication.getName());
        modelAndView.addObject("authors", authorService.findAll());
        modelAndView.addObject("account", account);
        modelAndView.setViewName("authorPanel");
        // <!--#authorization.expression('isAuthenticated()')-->
        //                                                    th:if="${ #strings.contains(#authentication.principal.authorities,'ADMIN') or #strings.contains(#authentication.principal.authorities,'USER')}"
        return modelAndView;
    }
}

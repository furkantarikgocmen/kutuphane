package com.ftg.kutuphane.controller;

import com.ftg.kutuphane.entitiy.Account;
import com.ftg.kutuphane.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final AccountService accountService;

    public LoginController(AccountService accountService) {
        this.accountService = accountService;
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
        //boolean isUser = (Arrays.toString(authentication.getAuthorities().toArray()).contains("USER"));
        Account account = accountService.findAccountByUserName(authentication.getName());

        modelAndView.addObject("account", account);
        modelAndView.setViewName("panel");
        return modelAndView;
    }
}

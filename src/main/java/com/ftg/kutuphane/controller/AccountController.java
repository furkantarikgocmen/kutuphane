package com.ftg.kutuphane.controller;

import com.ftg.kutuphane.entitiy.Account;
import com.ftg.kutuphane.entitiy.Author;
import com.ftg.kutuphane.enums.RoleId;
import com.ftg.kutuphane.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView panel(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accounts", accountService.findAll());
        modelAndView.addObject("account", accountService.getActiveAccount());
        modelAndView.setViewName("account/panelAccount");
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newAccountPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("account", accountService.getActiveAccount());
        Account account = new Account();
        modelAndView.addObject("accountObject", account);
        modelAndView.setViewName("account/newAccount");
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView newAccount(@ModelAttribute(value = "accountObject") Account account) {
        ModelAndView modelAndView = new ModelAndView();
        Account newAccount = new Account();
        modelAndView.addObject("account", accountService.getActiveAccount());
        modelAndView.addObject("state", accountService.newAccount(account));
        modelAndView.addObject("accountObject", newAccount);
        modelAndView.setViewName("account/newAccount");
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updatePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("account", accountService.getActiveAccount());
        modelAndView.setViewName("account/updateAccount");
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@Valid @ModelAttribute(value = "account") Account account) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("account", accountService.getActiveAccount());
        modelAndView.addObject("state",accountService.updateAccount(account));
        modelAndView.setViewName("account/updateAccount");
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView updateAccountPage(@PathVariable("id") UUID id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("account", accountService.getActiveAccount());
        modelAndView.addObject("anotherAccount", accountService.findById(id));
        modelAndView.setViewName("account/updateAccount");
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView updateAccount(@Valid @ModelAttribute(value = "account") Account account, @PathVariable("id") UUID id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("account", accountService.getActiveAccount());
        modelAndView.addObject("anotherAccount", accountService.findById(id));
        modelAndView.addObject("state",accountService.updateAccount(account));
        modelAndView.setViewName("account/updateAccount");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/id{id}", method = RequestMethod.GET)
    public ModelAndView deleteAccount(@PathVariable("id") UUID id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("state", accountService.delete(accountService.findById(id)));
        modelAndView.addObject("account", accountService.getActiveAccount());
        modelAndView.addObject("accounts", accountService.findAll());
        return modelAndView;
    }
}

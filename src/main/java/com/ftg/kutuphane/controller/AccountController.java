package com.ftg.kutuphane.controller;

import com.ftg.kutuphane.entitiy.Account;
import com.ftg.kutuphane.enums.RoleId;
import com.ftg.kutuphane.service.AccountService;
import com.ftg.kutuphane.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;
    private final RoleService roleService;

    public AccountController(AccountService accountService, RoleService roleService) {
        this.accountService = accountService;
        this.roleService = roleService;
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
    public ModelAndView newAccount(@ModelAttribute(value = "accountObject") Account account, @RequestParam(value = "rol") int rol) {
        ModelAndView modelAndView = new ModelAndView();
        Account newAccount = new Account();
        account.setRole(roleService.findById(rol));
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
        modelAndView.addObject("ownAccount", accountService.getActiveAccount());
        modelAndView.setViewName("account/updateAccount");
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@Valid @ModelAttribute(value = "ownAccount") Account account) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("state",accountService.updateAccount(account));
        modelAndView.addObject("ownAccount", accountService.getActiveAccount());
        modelAndView.addObject("account", accountService.getActiveAccount());
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
    public ModelAndView updateAccount(@Valid @ModelAttribute(value = "anotherAccount") Account account, @PathVariable("id") UUID id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("state",accountService.updateAccount(account));
        modelAndView.addObject("account", accountService.getActiveAccount());
        modelAndView.addObject("anotherAccount", accountService.findById(id));
        modelAndView.setViewName("account/updateAccount");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteAccount(@PathVariable("id") UUID id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("state", accountService.delete(accountService.findById(id)));
        modelAndView.addObject("account", accountService.getActiveAccount());
        modelAndView.addObject("accounts", accountService.findAll());
        modelAndView.setViewName("account/panelAccount");
        return modelAndView;
    }
}

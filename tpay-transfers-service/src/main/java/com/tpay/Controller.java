package com.tpay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    AccountsServiceClient accountsService;

    @GetMapping
    public String fromAccounts() {
        String fromAccounts = accountsService.hi();
        return "Transfers: Greeting from accounts - " + fromAccounts;
    }
}

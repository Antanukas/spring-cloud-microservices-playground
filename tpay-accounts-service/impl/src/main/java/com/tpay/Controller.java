package com.tpay;

import java.math.BigDecimal;

import com.tpay.events.BalanceUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller implements AccountsServiceClient {

    @Autowired
    private GreetingProperties props;

    @Autowired
    private ApplicationContext ctx;

    @Override
    public String hi() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        System.out.println(ctx.getApplicationName() + " NAME");
        ctx.publishEvent(new BalanceUpdatedEvent(this, ctx.getId(), new BigDecimal("20"), new BigDecimal("50")));
        return props.getGreeting();
    }
}

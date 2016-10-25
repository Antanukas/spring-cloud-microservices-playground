package com.tpay.events;

import java.math.BigDecimal;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class BalanceUpdatedEvent extends RemoteApplicationEvent {

    private BigDecimal amount;
    private BigDecimal newBalance;

    public BalanceUpdatedEvent() {
    }

    public BalanceUpdatedEvent(
            Object source,
            String originService,
            BigDecimal amount,
            BigDecimal newBalance) {

        super(source, originService);
        this.amount = amount;
        this.newBalance = newBalance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }
}

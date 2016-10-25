package com.tpay;

import com.tpay.events.BalanceUpdatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventListeners {

    @EventListener
    public void handleEvent(BalanceUpdatedEvent e) {
        System.out.println("Received sleeping");
        //Thread.sleep(10000);
        System.out.println(String.format(
                "Received event: %s %s %s %s",
                e.getOriginService(),
                e.getDestinationService(),
                e.getAmount(),
                e.getNewBalance()));
    }
}

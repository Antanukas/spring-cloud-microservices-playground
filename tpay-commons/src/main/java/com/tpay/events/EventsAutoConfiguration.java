package com.tpay.events;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "tpay.autoconfigure.events", havingValue = "true")
@RemoteApplicationEventScan({ "com.tpay.events" })
public class EventsAutoConfiguration {
}

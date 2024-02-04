package com.nextecommerce.commerce.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class EnvironmentConfig {

    @Value("${commerce.client.is-trustable}")
    public boolean trustHeaders;

    @Value("${commerce.client.real-ip-header}")
    public String trustedIpHeader;

    @Value("${commerce.client.real-port-header}")
    public String trustedPortHeader;

    @Value("${commerce.kafka.host}")
    public String kafkaAddresses;

    @Value("${commerce.kafka.producers.application}")
    public String applicationEventProducerName;

}

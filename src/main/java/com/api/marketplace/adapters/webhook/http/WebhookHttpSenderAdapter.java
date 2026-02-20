package com.api.marketplace.adapters.webhook.http;

import com.api.marketplace.domain.webhook.gateway.WebhookEventSenderGateway;
import com.api.marketplace.domain.webhook.model.WebhookEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WebhookHttpSenderAdapter implements WebhookEventSenderGateway {
    private final RestTemplate restTemplate;

    public WebhookHttpSenderAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void send(String callbackUrl, WebhookEvent event) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<WebhookEvent> request = new HttpEntity<>(event, headers);
            restTemplate.postForEntity(callbackUrl, request, Void.class);
            } catch (Exception e) {
                throw new RuntimeException("Failed to send webhook event to " + callbackUrl + ": " + e.getMessage());
            }
    }
}

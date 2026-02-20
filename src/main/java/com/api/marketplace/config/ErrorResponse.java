package com.api.marketplace.config;

public record ErrorResponse(
        int status,
        String error,
        String message
) {
}

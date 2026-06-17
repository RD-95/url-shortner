package com.urlshortener.service;

import com.urlshortener.model.UrlResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UrlShortenerService {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 7;

    private final Map<String, String> shortToOriginal = new ConcurrentHashMap<>();
    private final Map<String, String> originalToShort = new ConcurrentHashMap<>();
    private final SecureRandom random = new SecureRandom();

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    public UrlResponse shorten(String originalUrl) {
        // Return existing short code if URL was already shortened
        if (originalToShort.containsKey(originalUrl)) {
            String existingCode = originalToShort.get(originalUrl);
            return new UrlResponse(originalUrl, baseUrl + "/" + existingCode, existingCode);
        }

        String shortCode = generateUniqueCode();
        shortToOriginal.put(shortCode, originalUrl);
        originalToShort.put(originalUrl, shortCode);

        return new UrlResponse(originalUrl, baseUrl + "/" + shortCode, shortCode);
    }

    public Optional<String> getOriginalUrl(String shortCode) {
        return Optional.ofNullable(shortToOriginal.get(shortCode));
    }

    private String generateUniqueCode() {
        String code;
        do {
            code = generateCode();
        } while (shortToOriginal.containsKey(code));
        return code;
    }

    private String generateCode() {
        StringBuilder sb = new StringBuilder(SHORT_CODE_LENGTH);
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}

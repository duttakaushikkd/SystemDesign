package com.example.QuoteBackend.QuoteBackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class QuoteController {

    private final String[] quotes = {
            "Life is what happens when you're busy making other plans.",
            "You miss 100% of the shots you don’t take.",
            "Whether you think you can or you think you can’t, you’re right.",
            "The only way to do great work is to love what you do."
    };

    @CrossOrigin(origins = "*") // Enable CORS for all origins
    @GetMapping("/api/quote")
    public Quote getRandomQuote() {
        int index = new Random().nextInt(quotes.length);
        return new Quote(quotes[index]);
    }

    static class Quote {
        public String quote;
        public Quote(String quote) {
            this.quote = quote;
        }
    }
}

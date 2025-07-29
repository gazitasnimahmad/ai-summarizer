package com.ai.summarizer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Service
public class LamaAiService {
    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String summarize(String text) throws IOException, InterruptedException, URISyntaxException {
        // Create JSON payload
        String payload = objectMapper.writeValueAsString(
                objectMapper.createObjectNode()
                        .put("model", "mistral")
                        .put("prompt", "Summarize this:\n" + text)
                        .put("stream", false)
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(OLLAMA_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to summarize: " + response.body());
        }

        JsonNode json = objectMapper.readTree(response.body());
        return json.path("response").asText();
    }
}

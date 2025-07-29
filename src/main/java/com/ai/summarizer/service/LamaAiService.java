package com.ai.summarizer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.apache.tika.Tika;

@Service
public class LamaAiService {
    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Tika tika = new Tika();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String summarize(String text) throws IOException, InterruptedException, URISyntaxException {
        String prompt = "Summarize the following \n\n" + text;
        String response = callOllama( prompt);
        return response;
    }

    public String extractAndSummarize(MultipartFile file) throws IOException, TikaException, URISyntaxException, InterruptedException {
        // Step 1: Extract content using Apache Tika
        String content = tika.parseToString(file.getInputStream());

        // Step 3: Prompt for Ollama
        String prompt = "Ignore the image , just Summarize the following document text :\n\n" + content;

        // Step 4: Call local Ollama API
        return callOllama(prompt);
    }

    private String callOllama(String prompt) throws IOException, InterruptedException, URISyntaxException {
        String payload = objectMapper.writeValueAsString(
                objectMapper.createObjectNode()
                        .put("model", "mistral")
                        .put("prompt", prompt)
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

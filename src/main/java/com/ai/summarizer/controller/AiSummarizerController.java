package com.ai.summarizer.controller;

import com.ai.summarizer.dto.SummaryRequest;
import com.ai.summarizer.service.LamaAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class AiSummarizerController {

    @Autowired
    private LamaAiService openAiService;

    @PostMapping("/summarize")
    public ResponseEntity<String> chatWithAi(@RequestBody SummaryRequest message) throws Exception {
        String aiReply = openAiService.summarize(message.getText());
        return ResponseEntity.ok(aiReply);
    }

    @PostMapping("/upload-and-get-summary")
    public ResponseEntity<String> uploadAndSummarize(@RequestParam("file") MultipartFile file) {
        try {
            String summary = openAiService.extractAndSummarize(file);
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}

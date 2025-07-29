package com.ai.summarizer.controller;

import com.ai.summarizer.dto.SummaryRequest;
import com.ai.summarizer.service.LamaAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

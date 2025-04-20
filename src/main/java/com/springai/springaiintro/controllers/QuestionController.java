package com.springai.springaiintro.controllers;

import com.springai.springaiintro.model.*;
import com.springai.springaiintro.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final OpenAIService openAIService;

    public QuestionController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/capitalWithDetailedFormat")
    public GetCapitalDetailedResponse getCapitalWithDetailedFormat(@RequestBody GetCapitalRequest getCapitalRequest) {
        return openAIService.getCapitalWithDetailedFormat(getCapitalRequest);
    }

    @PostMapping("/capitalWithFormat")
    public GetCapitalResponse getCapitalWithFormat(@RequestBody GetCapitalRequest getCapitalRequest) {
        return openAIService.getCapitalWithFormat(getCapitalRequest);
    }

    @PostMapping("/capitalWithInfo")
    public Answer getCapitalWithInfo(@RequestBody GetCapitalRequest getCapitalRequest) {
        return openAIService.getCapitalWithInfo(getCapitalRequest);
    }

    @PostMapping("/capital")
    public Answer getCapital(@RequestBody GetCapitalRequest getCapitalRequest) {
        return openAIService.getCapital(getCapitalRequest);
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }
}

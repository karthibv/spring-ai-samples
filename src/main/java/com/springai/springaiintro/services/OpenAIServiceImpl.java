package com.springai.springaiintro.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springai.springaiintro.model.Answer;
import com.springai.springaiintro.model.GetCapitalRequest;
import com.springai.springaiintro.model.GetCapitalResponse;
import com.springai.springaiintro.model.Question;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final OpenAiChatModel chatModel;

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-format.st")
    private Resource getCapitalWithFormatPrompt;

    @Value("classpath:templates/get-capital-with-info.st")
    private Resource getGetCapitalPromptWithInfo;

    @Autowired
    ObjectMapper objectMapper;

    public OpenAIServiceImpl(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getText();
    }

    @Override
    public Answer getAnswer(Question question) {
        System.out.println("Question: " + question);
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }

    public GetCapitalResponse getCapitalWithFormat(GetCapitalRequest getCapitalRequest) {
        BeanOutputConverter<GetCapitalResponse> converter = new BeanOutputConverter<>(GetCapitalResponse.class);
        String format = converter.getFormat();
        System.out.println("Format: " + format);
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalWithFormatPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry(),
                "format", format));

        ChatResponse response = chatModel.call(prompt);
        System.out.println("Response: " + response.getResult().getOutput().getText());
        return converter.convert(response.getResult().getOutput().getText());
    }

    @Override
    public Answer getCapital(GetCapitalRequest getCapitalRequest) {
        System.out.println("getCapital: " + getCapitalRequest.stateOrCountry());
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
        ChatResponse response = chatModel.call(prompt);
        System.out.println("getCapital: output : " + response.getResult().getOutput().getText());
        String rawText = response.getResult().getOutput().getText();

        // 🧼 Clean markdown formatting if present
//        String cleanedText = rawText
//                .replaceAll("(?s)```json\\s*", "") // Remove opening ```json
//                .replaceAll("(?s)```", "")         // Remove closing ```
//                .trim();
        String responseString;
        try{
            JsonNode jsonNode = objectMapper.readTree(rawText);
            responseString = jsonNode.get("answer").asText();
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
        return new Answer(responseString);
    }


    @Override
    public Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {
        System.out.println("getCapitalWithInfo: " + getCapitalRequest.stateOrCountry());
        PromptTemplate promptTemplate = new PromptTemplate(getGetCapitalPromptWithInfo);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }


}
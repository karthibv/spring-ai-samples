package com.springai.springaiintro.services;

import com.springai.springaiintro.model.Answer;
import com.springai.springaiintro.model.GetCapitalRequest;
import com.springai.springaiintro.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenAIServiceImplTest {

    @Autowired
    OpenAIServiceImpl openAIService;

    @Test
    void getAnswer() {
        String answer = openAIService.getAnswer("25 - 4 * 2 + 3 = ?");
        System.out.println("Got the answer: getAnswer: " + answer);
    }

    @Test
    void testGetAnswer() {
        Question question= new Question("what is capital of singapore");
        Answer answer = openAIService.getAnswer(question);
        System.out.println("Got the answer: testGetAnswer :  " + answer.answer());
    }

    @Test
    void getCapital() {
        GetCapitalRequest getCapitalRequest= new GetCapitalRequest("what is capital of India");
        Answer answer = openAIService.getCapital(getCapitalRequest);
        System.out.println("Got the answer: getCapital : " + answer.answer());
    }

    @Test
    void getCapitalWithInfo() {
        GetCapitalRequest getCapitalRequest= new GetCapitalRequest("Singapore");
        Answer answer = openAIService.getCapitalWithInfo(getCapitalRequest);
        System.out.println("Got the answer: getCapital : " + answer.answer());
    }

}
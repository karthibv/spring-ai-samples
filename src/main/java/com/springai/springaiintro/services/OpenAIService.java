package com.springai.springaiintro.services;

import com.springai.springaiintro.model.Answer;
import com.springai.springaiintro.model.GetCapitalRequest;
import com.springai.springaiintro.model.GetCapitalResponse;
import com.springai.springaiintro.model.Question;

public interface OpenAIService {
    String getAnswer(String question);

    Answer getAnswer(Question question);

    Answer getCapital(GetCapitalRequest getCapitalRequest);

    GetCapitalResponse getCapitalWithFormat(GetCapitalRequest getCapitalRequest);

    Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest);

}

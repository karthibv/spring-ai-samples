package com.springai.springaiintro.services;

import com.springai.springaiintro.model.*;

public interface OpenAIService {
    String getAnswer(String question);

    Answer getAnswer(Question question);

    Answer getCapital(GetCapitalRequest getCapitalRequest);

    GetCapitalResponse getCapitalWithFormat(GetCapitalRequest getCapitalRequest);

    GetCapitalDetailedResponse getCapitalWithDetailedFormat(GetCapitalRequest getCapitalRequest);

    Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest);

}

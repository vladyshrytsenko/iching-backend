package com.example.iching.service;

import com.example.iching.model.CoinDetails;
import com.example.iching.model.Hexagram;
import com.example.iching.model.request.HexagramRequest;
import com.example.iching.model.response.HexagramResponse;
import com.example.iching.util.PromptGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;

@Service
@RequiredArgsConstructor
public class HexagramService {

    private final OpenAIService openAIService;
    private final RunwayAPIService runwayAPIService;

    public HexagramResponse getHexagramResponse(HexagramRequest request) {
        String question = request.getQuestion();
        List<Integer> coinResults = request.getCoinResults();

        if (isNotBlank(question) && !coinResults.isEmpty()) {
            CoinDetails coinDetails = new CoinDetails(coinResults);
            Hexagram hexagram = new Hexagram(coinDetails);
            String prompt = PromptGenerator.generateOpenAIPrompt(question);

            String openAIAnswer = this.openAIService.getChatCompletion(prompt);

            String runwayPrompt = PromptGenerator.generateRunwayPrompt(openAIAnswer, hexagram);
            String imageBase64 = this.runwayAPIService.getImageBase64(runwayPrompt);

            return new HexagramResponse(openAIAnswer, imageBase64);
        } else {
            throw new RuntimeException("Question or hexagram should not be blank");
        }
    }
}

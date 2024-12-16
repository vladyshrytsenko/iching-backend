package com.example.iching.service;

import com.example.iching.model.request.ChatCompletionRequest;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class OpenAIService {

    private final static String API_URL = "https://api.openai.com/v1/chat/completions";

    @Value("${openai.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public String getChatCompletion(String messageText) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer ".concat(apiKey));

            ChatCompletionRequest completionRequest = new ChatCompletionRequest("gpt-4o-mini", messageText);

            String requestBody = objectMapper.writeValueAsString(completionRequest);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(API_URL, requestEntity, String.class);

            String responseBody = responseEntity.getBody();
            assert responseBody != null;
            JSONObject jsonObject = new JSONObject(responseBody);

            return jsonObject.getJSONArray("choices").getJSONObject(0).getJSONObject("message").optString("content");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


package com.example.iching.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RunwayAPIService {

    private static final String RUNWAY_API_URL = "https://api.runware.ai/v1/models/stable-diffusion-v1/generate";

    @Value("${runway.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public String getImageBase64(String prompt) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + this.apiKey);

            List<Map<String, Object>> body = List.of(
                Map.of(
                    "taskType", "imageInference",
                    "taskUUID", "67aa2c57-3301-43ae-aa3b-dd40bbce2d33",
                    "positivePrompt", prompt,
                    "height", 1024,
                    "width", 1024,
                    "model", "runware:100@1",
                    "steps", 25,
                    "CFGScale", 4.0,
                    "numberResults", 1
                )
            );

            HttpEntity<List<Map<String, Object>>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(
                RUNWAY_API_URL,
                requestEntity,
                String.class
            );

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                String responseBody = responseEntity.getBody();
                if (responseBody != null) {
                    JSONObject jsonResponse = new JSONObject(responseBody);
                    JSONArray dataArray = jsonResponse.getJSONArray("data");
                    if (!dataArray.isEmpty()) {
                        JSONObject firstResult = dataArray.getJSONObject(0);
                        return firstResult.getString("imageURL");
                    } else {
                        throw new RuntimeException("No data in response: " + responseBody);
                    }
                }
            } else {
                throw new RuntimeException("Failed to get image from Runway API: " + responseEntity.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

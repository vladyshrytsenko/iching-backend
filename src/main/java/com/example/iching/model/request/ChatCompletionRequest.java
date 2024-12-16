package com.example.iching.model.request;

import com.example.iching.model.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatCompletionRequest {

    private String model;
    private List<ChatMessage> messages = new ArrayList<>();


    public ChatCompletionRequest(String model, String prompt) {
        this.model = model;
        this.messages.add(new ChatMessage("user", prompt));
    }
}

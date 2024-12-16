package com.example.iching.controller;

import com.example.iching.model.request.HexagramRequest;
import com.example.iching.model.response.HexagramResponse;
import com.example.iching.service.HexagramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hexagram")
@RequiredArgsConstructor
public class HexagramController {

    private final HexagramService hexagramService;

    @PostMapping("/ask-question")
    public ResponseEntity<HexagramResponse> postRequest(
        @RequestBody HexagramRequest request
    ) {
        HexagramResponse response = this.hexagramService.getHexagramResponse(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

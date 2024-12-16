package com.example.iching.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HexagramRequest {

    private String question;
    private List<Integer> coinResults;

}

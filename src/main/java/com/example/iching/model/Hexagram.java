package com.example.iching.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hexagram {
    private Line[] lines;

    public Hexagram(CoinDetails coinDetails) {
        this.lines = coinDetails.getLines();
    }
}


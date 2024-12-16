package com.example.iching.model;

import lombok.Data;

import java.util.List;

@Data
public class CoinDetails {

    private List<Integer> results;

    public CoinDetails(List<Integer> results) {
        this.results = results;
    }

    public Line[] getLines() {
        Line[] lines = new Line[2];

        for (int i = 0; i < 2; i++) {
            int sum = results.get(i);
            lines[i] = new Line(sum);
        }

        return lines;
    }
}


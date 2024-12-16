package com.example.iching.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Line {

    private boolean isYang;
    private boolean isChanging;

    public Line(int coinSum) {
        if (coinSum == 6) {
            this.isYang = false;
            this.isChanging = true;
        } else if (coinSum == 7) {
            this.isYang = true;
            this.isChanging = false;
        } else if (coinSum == 8) {
            this.isYang = false;
            this.isChanging = false;
        } else if (coinSum == 9) {
            this.isYang = true;
            this.isChanging = true;
        }
    }

}


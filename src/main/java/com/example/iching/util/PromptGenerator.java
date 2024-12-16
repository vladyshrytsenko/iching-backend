package com.example.iching.util;

import com.example.iching.model.Hexagram;
import com.example.iching.model.Line;

public class PromptGenerator {

    public static String generateOpenAIPrompt(String question) {
        StringBuilder prompt = new StringBuilder();

        prompt.append("Question: ").append(question).append(". ");
        prompt.append("Please, answer briefly in one sentence.");

        return prompt.toString();
    }

    public static String generateRunwayPrompt(String sentence, Hexagram hexagram) {
        StringBuilder prompt = new StringBuilder();

        prompt.append(sentence).append(". ");
        prompt.append("This hexagram represents: ").append(hexagramToString(hexagram)).append(". ");

        prompt.append("Create a vivid and symbolic image based on this hexagram. ");
        prompt.append("The image should include visual elements that reflect the balance of Yin and Yang, ");
        prompt.append("with a focus on harmony, transformation, and any relevant natural or mystical symbols. ");
        prompt.append("The scene should be atmospheric, with a clear connection to the question context. ");
        prompt.append("Include symbolic details like colors, textures, and lighting to enhance the visual impact.");

        return prompt.toString();
    }

    private static String hexagramToString(Hexagram hexagram) {
        StringBuilder hexagramStr = new StringBuilder();
        for (Line line : hexagram.getLines()) {
            hexagramStr.append(line.isYang() ? "Yang" : "Yin");
            hexagramStr.append(line.isChanging() ? " (Changing)" : " (Stable)");
            hexagramStr.append(", ");
        }
        if (!hexagramStr.isEmpty()) {
            hexagramStr.setLength(hexagramStr.length() - 2);
        }
        return hexagramStr.toString();
    }
}


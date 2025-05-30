package com.jal472.app;

import com.jal472.app.model.Character;
import com.jal472.app.parser.DnDBeyondParser;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        String url = "https://www.dndbeyond.com/characters/147122330";
        Character character = DnDBeyondParser.parseFromUrl(url);

        if (character != null) {
            System.out.println("Parsed character: \n" + character);
        } else {
            System.out.println("Failed to parse character.");
        }
    }
}

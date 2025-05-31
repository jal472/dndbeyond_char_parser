package com.jal472.app;

import com.jal472.app.model.Character;
import com.jal472.app.network.DnDHttpClient;
import com.jal472.app.parser.DnDBeyondParser;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        String url = "https://character-service.dndbeyond.com/character/v5/character/147122330";
        DnDHttpClient httpClient = new DnDHttpClient();
        DnDBeyondParser characterParser = new DnDBeyondParser(httpClient);
        Character character = characterParser.parseFromUrl(url);

        if (character != null) {
            System.out.println("Parsed character: \n" + character);
        } else {
            System.out.println("Failed to parse character.");
        }
    }
}

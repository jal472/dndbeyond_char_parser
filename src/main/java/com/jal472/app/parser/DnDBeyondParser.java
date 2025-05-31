package com.jal472.app.parser;

import com.jal472.app.model.Character;
import com.jal472.app.network.DnDHttpClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//import java.io.FileWriter;
//import java.io.IOException;

// No need for a constructor because this parser will just be used as a utility class for retrieving character data
public class DnDBeyondParser {
    private final DnDHttpClient httpClient;
    private static final String BASE_API = "https://character-service.dndbeyond.com/character/v5/character/";

    public DnDBeyondParser(DnDHttpClient httpClient) {
        this.httpClient = httpClient;
    }
    /**
     * Parses the character's information from a url and creates a Character object
     * @param url the dndbeyond character url
     * @return a constructed Character object
     */
    public Character parseFromUrl(String url) {
//        String name = "Test Name";
//        String race = "Humanoid";
//        String characterClass = "Sorcerer";
//        int level = 1;

        try {
            String characterId = extractCharacterId(url);
            String json = httpClient.get(BASE_API + characterId);
            return parseCharacterJson(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Extracts the character id from the dnd beyond url
     * @param url dndbeyond character url
     * @return the character id
     */
    private String extractCharacterId(String url) {
        String[] parts = url.split("/");
        return parts[parts.length - 1];
    }

    private Character parseCharacterJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);

        JsonNode data = root.path("data");

        String name = data.path("name").asText("Unknown");
        String race = data.path("race").path("fullName").asText("Unknown");
        String charClass = data.path("classes").get(0).path("definition").path("name").asText("Unknown");
        int level = data.path("classes").get(0).path("level").asInt(0);

        return new Character(name, race, charClass, level);
    }
}

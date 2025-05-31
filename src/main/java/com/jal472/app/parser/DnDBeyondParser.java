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

    /**
     * DnDBeyondParser constructor to initialize the httpClient to access dndbeyond character pages
     * @param httpClient the http client that makes requests to dndbeyond character pages
     */
    public DnDBeyondParser(DnDHttpClient httpClient) {
        this.httpClient = httpClient;
    }
    /**
     * Parses the character's information from a url and creates a Character object
     * @param url the dndbeyond character url
     * @return a constructed Character object
     */
    public Character parseFromUrl(String url) {
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
    /**
     * Parses the json body of the dndbeyond character page and creates the Character object
     * @param json json body of the dndbeyond character page
     * @return a constructed Character object
     * @throws Exception
     */
    private Character parseCharacterJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);

        JsonNode data = root.path("data");
        // Base Info - Fix: subclass, background, walkingSpeed
        String name = data.path("name").asText("Unknown");
        int level = data.path("classes").get(0).path("level").asInt(0);
        String species = data.path("race").path("fullName").asText("Unknown");
        String race = data.path("options").path("race").get(0).path("definition").path("name").asText("Unknown");
        String charClass = data.path("classes").get(0).path("definition").path("name").asText("Unknown");
        String charSubclass = data.path("classes").get(0).path("subclassDefinition").path("name").asText("Unknown"); // subclassDefinition.name
        String background = data.path("background").path("name").asText("Unknown"); // background.name
        int baseHp = data.path("baseHitPoints").asInt(0); // baseHitPoints
        int bonusHp = data.path("bonusHitPoints").asInt(0); // bonusHitPoints
        int xp = data.path("currentXp").asInt(0);; // currentXp
        int walkingSpeed = data.path("weightSpeeds").path("normal").path("walk").asInt(0);; // weightSpeeds.normal.walk
        boolean heroicInspiration = data.path("inspriation").asBoolean(false); // inspiration
        int gp = data.path("currencies").path("gp").asInt(0); // currencies.gp

        Character.CharacterBaseInfo baseInfo = new Character.CharacterBaseInfo(name, level, species, race, charClass,
                charSubclass, background, baseHp, bonusHp, xp, walkingSpeed, heroicInspiration, gp);
        return new Character(baseInfo);
    }
}

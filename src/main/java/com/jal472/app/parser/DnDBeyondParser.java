package com.jal472.app.parser;

import com.jal472.app.model.Character;
import com.jal472.app.network.DnDHttpClient;
import com.jal472.app.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Iterator;

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
     * Parses the character's information from the character id and creates a Character object
     * @param id the dndbeyond character id
     * @return a constructed Character object
     */
    public Character parseFromId(int id) {
        try {
            String json = httpClient.get(BASE_API + id);
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
        // Base Info
        String name = JsonUtils.getSafeText(data, "Unknown", "name");
        int level = JsonUtils.getSafeIntFromArray(data.path("classes"), 0, 0, "level");
        String species = JsonUtils.getSafeText(data, "Unknown", "race", "fullName");
        String race = JsonUtils.getSafeTextFromArray(data.path("options").path("race"), 0, "Unknown", "definition", "name");
        String charClass = JsonUtils.getSafeTextFromArray(data.path("classes"), 0, "Unknown", "definition", "name");
        String charSubclass = JsonUtils.getSafeTextFromArray(data.path("classes"), 0, "Unknown", "subclassDefinition", "name");
        String background = JsonUtils.getSafeText(data, "Unknown", "background", "definition", "name");
        int baseHp = JsonUtils.getSafeInt(data, 0, "baseHitPoints");
        int bonusHp = JsonUtils.getSafeInt(data, 0, "bonusHitPoints");
        int xp = JsonUtils.getSafeInt(data, 0, "currentXp");
        int walkingSpeed = JsonUtils.getSafeInt(data, 0, "race", "weightSpeeds", "normal", "walk");
        boolean heroicInspiration = JsonUtils.getSafeBoolean(data, false, "inspiration");
        int gp = JsonUtils.getSafeInt(data, 0, "currencies", "gp");

        Character.CharacterBaseInfo baseInfo = new Character.CharacterBaseInfo(name, level, species, race, charClass,
                charSubclass, background, baseHp, bonusHp, xp, walkingSpeed, heroicInspiration, gp);
        return new Character(baseInfo);
    }
}

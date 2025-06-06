package com.jal472.app.parser;

import com.jal472.app.model.Character;
import com.jal472.app.network.DnDHttpClient;
import com.jal472.app.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// No need for a constructor because this parser will just be used as a utility class for retrieving character data
public class DnDBeyondParser {
    private final DnDHttpClient httpClient = new DnDHttpClient();;
    private static final String BASE_API = "https://character-service.dndbeyond.com/character/v5/character/";

    /**
     * Parses the character's information from the character id and creates a Character object
     * @param id the dndbeyond character id
     * @return a constructed Character object
     */
    public Character parseFromId(int id) {
        try {
            System.out.println("Thread " + Thread.currentThread().getName() + " is parsing character " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        try {
            String json = httpClient.get(BASE_API + id);
            return parseCharacterJson(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

        Character.CharacterBaseInfo baseInfo = new Character.CharacterBaseInfo(
                name, level, species,
                race, charClass, charSubclass,
                background, baseHp, bonusHp,
                xp, walkingSpeed, heroicInspiration,
                gp);
        return new Character(baseInfo);
    }
}

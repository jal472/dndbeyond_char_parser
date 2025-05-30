package com.jal472.app.parser;

import com.jal472.app.model.Character;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

// No need for a constructor because this parser will just be used as a utility class for retrieving character data
public class DnDBeyondParser {

    /**
     * Parses the character's information from a url and creates a Character object
     * @param url the dndbeyond character url
     * @return a constructed Character object
     */
    public static Character parseFromUrl(String url) {
        try {
            String name = "Test Name";
            String race = "Humanoid";
            String characterClass = "Sorcerer";
            int level = 1;

            Document doc = Jsoup.connect(url).get();

            return new Character(name, race, characterClass, level);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

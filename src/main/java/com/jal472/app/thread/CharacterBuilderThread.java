package com.jal472.app.thread;

import com.jal472.app.model.Character;
import com.jal472.app.parser.DnDBeyondParser;

public class CharacterBuilderThread extends Thread {
    private int characterId = 0;
    private Character character;
    private final DnDBeyondParser characterParser = new DnDBeyondParser();

    public CharacterBuilderThread(String dndbeyondUrl) {
        characterId = extractCharacterId(dndbeyondUrl);
    }
    public CharacterBuilderThread(int characterId) {
        this.characterId = characterId;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread " + Thread.currentThread().getId() + " is building character " + characterId);
        } catch (Exception e) {
            System.out.println("Exception is caught");
            e.printStackTrace();
        }

        character = characterParser.parseFromId(characterId);
        if (character != null) {
            System.out.println("Parsed character - thread(" + Thread.currentThread().getId() + "): \n" + character);
        } else {
            System.out.println("Failed to parse character " + characterId + " - thread(" + Thread.currentThread().getId() + ")");
        }
    }

    /**
     * Extracts the character id from the dnd beyond url
     * @param url dndbeyond character url
     * @return the character id
     */
    private int extractCharacterId(String url) {
        String[] parts = url.split("/");
        return Integer.parseInt(parts[parts.length - 1]);
    }
}

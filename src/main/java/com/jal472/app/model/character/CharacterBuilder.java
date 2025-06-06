package com.jal472.app.model.character;

import com.jal472.app.model.Character;
import com.jal472.app.parser.DnDBeyondParser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CharacterBuilder {
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private final DnDBeyondParser characterParser = new DnDBeyondParser();

    public Future<Character> build(int characterId) {
//        int characterId = extractCharacterId(dndBeyondUrl);
        return executorService.submit(() -> characterParser.parseFromId(characterId));
    }

    public void shutdown() {
        executorService.shutdown();
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

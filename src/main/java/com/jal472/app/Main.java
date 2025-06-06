package com.jal472.app;

import com.jal472.app.model.Character;
import com.jal472.app.model.character.CharacterBuilder;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        // Eventually, these characters will be loaded up to be viewed in a webpage
        // This feature will likely not require multithreading but when a user inputs a new character url it will be
        // loaded into the page as a card with the all the characters of the campaign's info on one page
        ArrayList<Integer> characterIds = new ArrayList<>();
        characterIds.add(147122330);
        characterIds.add(143507564);
        characterIds.add(141975397);
        characterIds.add(50982129);
        characterIds.add(147118858);

        ArrayList<Character> characters = new ArrayList<>();
        CharacterBuilder charBuilder = new CharacterBuilder();
        for (int id : characterIds) {
            Future<Character> futureCharacter = charBuilder.build(id);
            try {
                characters.add(futureCharacter.get());
            } catch (ExecutionException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        for (Character character : characters) {
            System.out.println(character);
        }
        charBuilder.shutdown();
    }
}

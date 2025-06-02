package com.jal472.app;

import com.jal472.app.model.Character;
import com.jal472.app.network.DnDHttpClient;
import com.jal472.app.parser.DnDBeyondParser;
import com.jal472.app.thread.CharacterBuilderThread;

import java.util.ArrayList;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        // Eventually, these characters will be loaded up to be viewed in a webpage
        // This feature will likely not require multithreading but when a user inputs a new character url it will be
        // loaded into the page as a card with the all the characters of the campaign's info on one page
        ArrayList<Integer> characterIds = new ArrayList<Integer>();
        characterIds.add(147122330);
        characterIds.add(143507564);
        characterIds.add(141975397);
        characterIds.add(50982129);
        characterIds.add(147118858);

        for (int id : characterIds) {
            CharacterBuilderThread thread = new CharacterBuilderThread(id);
            thread.start();
        }

        // TODO: Work on getting being able to pass the result to be able to load into the webpage.
    }
}

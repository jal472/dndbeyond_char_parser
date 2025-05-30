package com.jal472.app.model;

public class Character {
    private String name;
    private String race;
    private String characterClass;
    private int level;
    /**
     * Character class constructor
     * @param name character's name
     * @param race character's race
     * @param characterClass character's class
     * @param level character's level
     */
    public Character(String name, String race, String characterClass, int level) {
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.level = level;
    }

    /**
     * Character name getter
     * @return character's name
     */
    public String getName() {
        return name;
    }

    /**
     * Character race getter
     * @return character's race
     */
    public String getRace() {
        return race;
    }

    /**
     * Character class getter
     * @return character's class
     */
    public String getCharacterClass() {
        return characterClass;
    }

    /**
     * Character level getter
     * @return character's level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Overwritten toString method to print the Character nicely
     * @return string describing the Character object
     */
    @Override
    public String toString() {
        return name + " [level: " + level + " | race: " + race + " | class: " + characterClass + "]";
    }

}

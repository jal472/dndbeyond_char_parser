package com.jal472.app.model;

public class Character {
    private CharacterBaseInfo baseInfo;
    // (stats.n.value) - 1 strength, 2 dexterity, 3 constitution, 4 intelligence, 5 wisdom, 6 charisma
//    private CharacterStats;
    // acrobatics, animalHandling, arcana, athletics, deception, history, insight, intimidation, investigation, medicine,
    // nature, perception, performance, persuasion, religion, sleightOfHand, stealth, survival
//    private CharacterSkills;
    // strengthSavingThrowModifiers[] - list of potential strength saving throw modifiers
//    private CharacterSavingThrowMods;
    /**
     * Character class constructor
     * @param baseInfo character's baseInfo - see CharacterBaseInfo nested class
     */
    public Character(CharacterBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }
    /**
     * Overwritten toString method to print the Character nicely
     * @return string describing the Character object
     */
    @Override
    public String toString() {
        return baseInfo.toString();
    }
    /**
     *
     * @return
     */
    public CharacterBaseInfo getBaseInfo() {
        return baseInfo;
    }
    /**
     *
     * @param baseInfo
     */
    public void setBaseInfo(CharacterBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }
    /**
     * Character name getter
     * @return character's name
     */
    public String getName() {
        return baseInfo.getName();
    }

    /**
     * Character race getter
     * @return character's race
     */
    public String getRace() {
        return baseInfo.getRace();
    }

    /**
     * Character class getter
     * @return character's class
     */
    public String getCharacterClass() {
        return baseInfo.getCharacterClass();
    }

    /**
     * Character level getter
     * @return character's level
     */
    public int getLevel() {
        return baseInfo.getLevel();
    }

    /**
     * Object that encapsulates the character base info
     */
    public static class CharacterBaseInfo {
        private String name; // already have
        private int level; // already have
        private String species; // what race is
        private String race; // options.race.definition.name
        private String characterClass; // already have
        private String characterSubclass; // subclassDefinition.name
        private String background; // background.name
        private int baseHp; // baseHitPoints
        private int bonusHp; // bonusHitPoints
        private int xp; // currentXp
        private int walkingSpeed; // weightSpeeds.normal.walk
        private boolean heroicInspiration; // inspiration
        private int gp; // currencies.gp

        public CharacterBaseInfo(String name, int level, String species, String race, String characterClass,
                                 String characterSubclass, String background, int baseHp, int bonusHp, int xp,
                                 int walkingSpeed, boolean heroicInspiration, int gp) {
            this.name = name;
            this.level = level;
            this.species = species;
            this.race = race;
            this.characterClass = characterClass;
            this.characterSubclass = characterSubclass;
            this.background = background;
            this.baseHp = baseHp;
            this.bonusHp = bonusHp;
            this.xp = xp;
            this.walkingSpeed = walkingSpeed;
            this.heroicInspiration = heroicInspiration;
            this.gp = gp;
        }
        @Override
        public String toString() {
            return name +
                    "\n\tlevel: " + level +
                    "\n\tspecies: " + species +
                    "\n\trace: " + race +
                    "\n\tclass: " + characterClass +
                    "\n\tsubclass: " + characterSubclass +
                    "\n\tbackground: " + background +
                    "\n\tbaseHp: " + baseHp +
                    "\n\tbonusHp: " + bonusHp +
                    "\n\txp: " + xp +
                    "\n\twalkingSpeed: " + walkingSpeed + " ft" +
                    "\n\theroicInspiration: " + heroicInspiration +
                    "\n\tgp: " + gp;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getSpecies() {
            return species;
        }

        public void setSpecies(String species) {
            this.species = species;
        }

        public String getRace() {
            return race;
        }

        public void setRace(String race) {
            this.race = race;
        }

        public String getCharacterClass() {
            return characterClass;
        }

        public void setCharacterClass(String characterClass) {
            this.characterClass = characterClass;
        }

        public String getCharacterSubclass() {
            return characterSubclass;
        }

        public void setCharacterSubclass(String characterSubclass) {
            this.characterSubclass = characterSubclass;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public int getBaseHp() {
            return baseHp;
        }

        public void setBaseHp(int baseHp) {
            this.baseHp = baseHp;
        }

        public int getBonusHp() {
            return bonusHp;
        }

        public void setBonusHp(int bonusHp) {
            this.bonusHp = bonusHp;
        }

        public int getXp() {
            return xp;
        }

        public void setXp(int xp) {
            this.xp = xp;
        }

        public int getWalkingSpeed() {
            return walkingSpeed;
        }

        public void setWalkingSpeed(int walkingSpeed) {
            this.walkingSpeed = walkingSpeed;
        }

        public boolean isHeroicInspiration() {
            return heroicInspiration;
        }

        public void setHeroicInspiration(boolean heroicInspiration) {
            this.heroicInspiration = heroicInspiration;
        }

        public int getGp() {
            return gp;
        }

        public void setGp(int gp) {
            this.gp = gp;
        }
    }
    // (stats.n.value) - 1 strength, 2 dexterity, 3 constitution, 4 intelligence, 5 wisdom, 6 charisma
//    private CharacterStats;
    // acrobatics, animalHandling, arcana, athletics, deception, history, insight, intimidation, investigation, medicine,
    // nature, perception, performance, persuasion, religion, sleightOfHand, stealth, survival
//    private CharacterSkills;
    // strengthSavingThrowModifiers[] - list of potential strength saving throw modifiers
//    private CharacterSavingThrowMods;
    // customSenses[] - senses[] - list of potential senses
    public static class CharacterSenses{

    }
    // customProficiencies[] - armor[], weapons[], tools[], languages[]
    public static class CharacterProficiencies {

    }
    // inventory (n.definition.name)
    public static class CharacterInventory {

    }
}

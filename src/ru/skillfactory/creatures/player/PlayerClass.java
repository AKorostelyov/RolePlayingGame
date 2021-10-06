package ru.skillfactory.creatures.player;

public enum PlayerClass {
    KNIGHT("Knight",
            "strong warrior, but a little slow",
            18,
            50,
            5,
            8,
            4,
            3,
            8),
    HUNTER("Hunter",
            "fast unit, who can avoid monsters attacks",
            14,
            20,
            7,
            5,
            9,
            5,
            6),
    ASSASSIN("Assassin",
            "deadly unit, but not so observant",
            16,
            18,
            9,
            17,
            5,
            8,
            4),
    WIZARD("Wizard",
            "weak unit, with amazing damage",
            12,
            30,
            6,
            25,
            5,
            6,
            0),
    PEASANT("Peasant",
            "HARDCORE",
            8,
            5,
            6,
            3,
            4,
            7,
            3);

    private String playerClass;
    private String desctiption;
    private int maxHealth;
    private double money;
    private int agilityLvl;
    private int strengthLvl;
    private int perceptionLvl;
    private int luckLvl;
    private int defence;


    PlayerClass(String playerClass, String desctiption, int maxHealth, double money, int agilityLvl, int strengthLvl, int perceptionLvl, int luckLvl, int defence) {
        this.desctiption = desctiption;
        this.maxHealth = maxHealth;
        this.money = money;
        this.agilityLvl = agilityLvl;
        this.strengthLvl = strengthLvl;
        this.perceptionLvl = perceptionLvl;
        this.luckLvl = luckLvl;
        this.defence = defence;
        this.playerClass = playerClass;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public double getMoney() {
        return money;
    }

    public int getAgilityLvl() {
        return agilityLvl;
    }

    public int getStrengthLvl() {
        return strengthLvl;
    }

    public int getPerceptionLvl() {
        return perceptionLvl;
    }

    public int getLuckLvl() {
        return luckLvl;
    }

    public int getDefence() {
        return defence;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public static String getClassDescription() {
        StringBuilder classesDescription = new StringBuilder();
        for (int i = 0; i < PlayerClass.values().length; i++) {
            classesDescription.append(i + 1)
                    .append(". ")
                    .append(PlayerClass.values()[i].playerClass)
                    .append(": ")
                    .append(PlayerClass.values()[i].desctiption)
                    .append("\n");
        }
        return classesDescription.toString();
    }
}

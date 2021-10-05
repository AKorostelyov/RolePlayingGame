package ru.skillfactory.creatures.player;

import ru.skillfactory.game.Configuration;
import ru.skillfactory.game.GameMessages;
import ru.skillfactory.maintenance.Printer;

/**
 * Класс, описывающий героя игры
 */
public class Player {
    private static volatile Player instance;
    private boolean isAlive;
    private String name;
    private int maxHealth;
    private int health;
    private double money;
    private int lvl;
    private int agilityLvl;
    private int strengthLvl;
    private int perceptionLvl;
    private int experienceToLvl;
    private int luckLvl;
    private int defence;
    private String playerClass;

    public static Player getInstance() {
        Player localInstance = instance;
        if (localInstance == null) {
            synchronized (Player.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Player();
                }
            }
        }
        return localInstance;
    }

    public int getLuckLvl() {
        return luckLvl;
    }

    public void setLuckLvl(int luckLvl) {
        this.luckLvl = luckLvl;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    protected void printCharacterDescription() {
        Printer.formatPrint(GameMessages.COMMON_CHARACTER_DESCRIPTION_MESSAGE,
                String.valueOf(this.health),
                String.valueOf(this.money),
                String.valueOf(this.agilityLvl),
                String.valueOf(this.perceptionLvl),
                String.valueOf(this.strengthLvl),
                String.valueOf(this.luckLvl));
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getAgilityLvl() {
        return agilityLvl;
    }

    public void setAgilityLvl(int agilityLvl) {
        this.agilityLvl = agilityLvl;
    }

    public int getStrengthLvl() {
        return strengthLvl;
    }

    public void setStrengthLvl(int strengthLvl) {
        this.strengthLvl = strengthLvl;
    }

    public int getPerceptionLvl() {
        return perceptionLvl;
    }

    public void setPerceptionLvl(int perceptionLvl) {
        this.perceptionLvl = perceptionLvl;
    }

    public int getExperienceToLvl() {
        return experienceToLvl;
    }

    public void setExperienceToLvl(int experienceToLvl) {
        this.experienceToLvl = experienceToLvl;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getDamage(int damage) {
        this.health -= damage;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void refillHealth(int points) {
        this.health = Math.min(this.health + points, this.maxHealth);
    }

    public void fullFillHealth() {
        this.health = this.maxHealth;
    }

    public void confirmDeath() {
        this.isAlive = false;
        this.health = 0;
    }

    public void setHunterClass(String name) {
        this.name = name;
        this.health = 14;
        this.maxHealth = 14;
        this.money = 20;
        this.lvl = 1;
        this.agilityLvl = 8;
        this.strengthLvl = 5;
        this.perceptionLvl = 9;
        this.luckLvl = 5;
        this.experienceToLvl = Configuration.EXP_POINTS_TO_LVL;
        this.defence = 6;
        this.isAlive = true;
        this.playerClass = "Hunter";
        printCharacterDescription();
    }

    public void setKnightClass(String name) {
        this.name = name;
        this.health = 18;
        this.maxHealth = 18;
        this.money = 50;
        this.lvl = 1;
        this.agilityLvl = 5;
        this.strengthLvl = 8;
        this.perceptionLvl = 4;
        this.luckLvl = 3;
        this.experienceToLvl = Configuration.EXP_POINTS_TO_LVL;
        this.defence = 8;
        this.isAlive = true;
        this.playerClass = "Knight";
        printCharacterDescription();
    }

    public void setPeasantClass(String name) {
        this.name = name;
        this.health = 8;
        this.maxHealth = 8;
        this.money = 5;
        this.lvl = 1;
        this.agilityLvl = 3;
        this.strengthLvl = 3;
        this.perceptionLvl = 4;
        this.luckLvl = 7;
        this.experienceToLvl = Configuration.EXP_POINTS_TO_LVL;
        this.defence = 3;
        this.isAlive = true;
        this.playerClass = "Peasant";
        printCharacterDescription();
    }

    public void setWizardClass(String name) {
        this.name = name;
        this.health = 12;
        this.maxHealth = 12;
        this.money = 30;
        this.lvl = 1;
        this.agilityLvl = 6;
        this.strengthLvl = 20;
        this.perceptionLvl = 5;
        this.luckLvl = 6;
        this.experienceToLvl = Configuration.EXP_POINTS_TO_LVL;
        this.defence = 0;
        this.isAlive = true;
        this.playerClass = "Wizard";
        printCharacterDescription();
    }
}

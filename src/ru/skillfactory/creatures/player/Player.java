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

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
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
        Printer.formatPrint(GameMessages.PLAYER_HEALTH_REDEEMED_MESSAGE, this.name, String.valueOf(points));
        Printer.formatPrint(GameMessages.STATS_HEALTH_MESSAGE, this.name, String.valueOf(this.health),String.valueOf(this.maxHealth));
    }

    public void fullFillHealth() {
        this.health = this.maxHealth;
        Printer.formatPrint(GameMessages.PLAYER_HEALTH_FULFILLED_MESSAGE, this.name);
    }

    public void confirmDeath() {
        this.isAlive = false;
        this.health = 0;
    }

    public void upgradeDefence(int upgradeSize) {
        this.defence += upgradeSize;
        Printer.formatPrint(GameMessages.PLAYER_DEFENCE_UPGRADED_MESSAGE, this.name, String.valueOf(upgradeSize),String.valueOf(this.defence));
    }

    public void updgradeWeapon(int upgradeSize) {
        this.strengthLvl += upgradeSize;
        Printer.formatPrint(GameMessages.PLAYER_DAMAGE_UPGRADED_MESSAGE, this.name, String.valueOf(upgradeSize),String.valueOf(this.strengthLvl));
    }

    public void upgradePerception(int upgradeSize) {
        this.perceptionLvl += upgradeSize;
        Printer.formatPrint(GameMessages.PLAYER_PERCEPTION_UPGRADED_MESSAGE, this.name, String.valueOf(upgradeSize),String.valueOf(this.perceptionLvl));
    }

    public void upgradeLuck(int upgradeSize) {
        this.luckLvl += upgradeSize;
        Printer.formatPrint(GameMessages.PLAYER_LUCK_UPGRADED_MESSAGE, this.name, String.valueOf(upgradeSize),String.valueOf(this.luckLvl));
    }

    public void upgradeAgility(int upgradeSize) {
        this.agilityLvl += upgradeSize;
        Printer.formatPrint(GameMessages.PLAYER_AGILITY_UPGRADED_MESSAGE, this.name, String.valueOf(upgradeSize),String.valueOf(this.agilityLvl));
    }

    public void setPlayerClass(String name,int classIndex) {
        PlayerClass playerClass = PlayerClass.values()[classIndex];
        this.name = name;
        this.maxHealth = playerClass.getMaxHealth();
        this.health = this.maxHealth;
        this.money = playerClass.getMoney();
        this.lvl = 1;
        this.agilityLvl = playerClass.getAgilityLvl();
        this.strengthLvl = playerClass.getStrengthLvl();
        this.perceptionLvl = playerClass.getPerceptionLvl();
        this.luckLvl = playerClass.getLuckLvl();
        this.experienceToLvl = Configuration.EXP_POINTS_TO_LVL;
        this.defence = playerClass.getDefence();
        this.isAlive = true;
        this.playerClass = playerClass.getPlayerClass();
        printCharacterDescription();
    }
}

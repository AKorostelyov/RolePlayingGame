package ru.skillfactory.creatures.monsters;

/**
 * Класс, описывающий монстра в игре
 */
public abstract class Monster {
    protected int health;
    protected int attackDamage;
    protected int perceptionLvl;
    protected int agilityLvl;
    protected int experienceBonus;
    protected int goldBonus;

    public int getGoldBonus() {
        return goldBonus;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getPerceptionLvl() {
        return perceptionLvl;
    }

    public int getAgilityLvl() {
        return agilityLvl;
    }

    public void setAgilityLvl(int agilityLvl) {
        this.agilityLvl = agilityLvl;
    }

    public int getExperienceBonus() {
        return experienceBonus;
    }

    public void setExperienceBonus(int experienceBonus) {
        this.experienceBonus = experienceBonus;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttackDamage(int damage) {
        this.attackDamage = damage;
    }

    public void setPerceptionLvl(int perceptionLvl) {
        this.perceptionLvl = perceptionLvl;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }
}

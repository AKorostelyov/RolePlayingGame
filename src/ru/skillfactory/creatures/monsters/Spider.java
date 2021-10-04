package ru.skillfactory.creatures.monsters;

import ru.skillfactory.game.GameMessages;
import ru.skillfactory.maintenance.Printer;

public class Spider extends Monster{
    public Spider() {
        this.health = 50;
        this.agilityLvl = 6;
        this.attackDamage = 18;
        this.perceptionLvl = 7;
        this.experienceBonus = 10;
        Printer.print(GameMessages.MONSTER_SPIDER_MESSAGE);
    }
}

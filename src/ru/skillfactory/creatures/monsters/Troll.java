package ru.skillfactory.creatures.monsters;

import ru.skillfactory.game.GameMessages;
import ru.skillfactory.maintenance.Printer;

public class Troll extends Monster {
    public Troll() {
        this.health = 60;
        this.agilityLvl = 4;
        this.attackDamage = 26;
        this.perceptionLvl = 2;
        this.experienceBonus = 12;
        this.goldBonus = 7;
        Printer.print(GameMessages.MONSTER_TROLL_MESSAGE);
    }
}

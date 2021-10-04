package ru.skillfactory.creatures.monsters;

import ru.skillfactory.game.GameMessages;
import ru.skillfactory.maintenance.Printer;

public class Bandit extends Monster {
    public Bandit() {
        this.health = 9;
        this.agilityLvl = 6;
        this.attackDamage = 7;
        this.perceptionLvl = 5;
        this.experienceBonus = 3;

        Printer.print(GameMessages.MONSTER_BANDIT_MESSAGE);
    }
}

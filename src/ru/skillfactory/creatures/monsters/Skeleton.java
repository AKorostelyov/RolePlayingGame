package ru.skillfactory.creatures.monsters;

import ru.skillfactory.game.Game;
import ru.skillfactory.game.GameMessages;
import ru.skillfactory.maintenance.Printer;

public class Skeleton extends Monster {
    public Skeleton() {
        this.health = 4;
        this.agilityLvl = 4;
        this.attackDamage = 4;
        this.perceptionLvl = 3;
        this.experienceBonus = 2;
        this.goldBonus = 2;
        Printer.print(GameMessages.MONSTER_SKELETON_MESSAGE);
    }
}

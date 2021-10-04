package ru.skillfactory.creatures.monsters;

import ru.skillfactory.game.GameMessages;
import ru.skillfactory.maintenance.Printer;

public class Dragon extends Monster {
    public Dragon() {
        this.health = 800;
        this.agilityLvl = 12;
        this.attackDamage = 80;
        this.perceptionLvl = 15;
        this.experienceBonus = 80;
        Printer.print(GameMessages.MONSTER_DRAGON_MESSAGE);
    }
}

package ru.skillfactory.battles;

import ru.skillfactory.creatures.monsters.*;
import ru.skillfactory.creatures.player.Player;
import ru.skillfactory.game.GameMessages;
import ru.skillfactory.game.Statistic;
import ru.skillfactory.maintenance.Printer;

import java.util.Random;

/**
 * Класс, описывающий процесс битвы
 */
public class BattleHelper {

    private static final int LOW_LEVEL_ENEMY = 3;
    private static final int MID_LEVEL_ENEMY = 7;

    /**
     * Процесс битвы
     *
     * @param monster текущий противник героя
     */
    public static void startBattle(Monster monster) {
        boolean isPlayerFirst = Player.getInstance().getPerceptionLvl() > monster.getPerceptionLvl();

        if (isPlayerFirst) {
            monsterTakeDamage(monster);
        } else {
            playerTakeDamage(monster);
        }

        while (Player.getInstance().getHealth() > 0 && monster.getHealth() > 0) {
            if (!isPlayerFirst) {
                monsterTakeDamage(monster);
                isPlayerFirst = true;
            } else {
                playerTakeDamage(monster);
                isPlayerFirst = false;
            }
        }

        if (Player.getInstance().getHealth() <= 0) {
            Player.getInstance().confirmDeath();
        } else {
            Printer.formatPrint(GameMessages.BATTLE_MONSTER_KILL_MESSAGE, monster.getClass().getSimpleName(), Player.getInstance().getName(), String.valueOf(Player.getInstance().getHealth()));
            ExperienceHelper.updateExpPlayer(monster.getExperienceBonus());
            BonusHelper.earnCoins();
            Statistic.addMonster(monster);
        }
    }

    /**
     * Метод получения урона монстром
     * @param monster монстр
     */
    private static void monsterTakeDamage(Monster monster) {
        int damage = DamageHelper.calculatePlayerDamage();
        if (damage == 0)
            Printer.formatPrint(GameMessages.BATTLE_MISSED_HIT, Player.getInstance().getName());
        else {
            monster.takeDamage(damage);
            Printer.formatPrint(GameMessages.BATTLE_MONSTER_HIT_MESSAGE, monster.getClass().getSimpleName(), String.valueOf(damage));
        }
    }

    /**
     * Метод получения урона персонажем
     * @param monster монстр
     */
    private static void playerTakeDamage(Monster monster) {
        int damage = DamageHelper.calculateMonsterDamage(monster.getAttackDamage(), monster.getAgilityLvl());
        if (damage == 0) {
            Printer.formatPrint(GameMessages.BATTLE_MISSED_HIT, monster.getClass().getSimpleName());
        } else {
            int ignoredDamage = Player.getInstance().getDefence();
            if (ignoredDamage != 0)
                Printer.formatPrint(GameMessages.BATTLE_PLAYER_IGNORED_DAMAGE_MESSAGE, Player.getInstance().getName(), String.valueOf(Math.min(ignoredDamage, damage)));
            if (Math.max(damage - ignoredDamage, 0) != 0) {
                Printer.formatPrint(GameMessages.BATTLE_PLAYER_HIT_MESSAGE, Player.getInstance().getName(), String.valueOf(Math.max(damage - ignoredDamage, 0)));
                Player.getInstance().getDamage(damage - ignoredDamage);
            }
        }

    }

    /**
     * Подбор противника для героя и начало битвы
     */
    public static void initBattle() {
        Monster monster = getRival();
        startBattle(monster);
    }

    /**
     * Подбор противника
     *
     * @return противник
     */
    private static Monster getRival() {
        Random random = new Random();

        if (Player.getInstance().getLvl() <= LOW_LEVEL_ENEMY) {
            return (random.nextBoolean()) ? new Skeleton() : new Bandit();
        } else if (Player.getInstance().getLvl() <= MID_LEVEL_ENEMY) {
            int monster = random.nextInt(4);
            switch (monster){
                case 1:
                    return new Bandit();
                case 2:
                    return new Troll();
                default:
                    return new Spider();
            }
        } else {
            int monster = random.nextInt(5);
            switch (monster){
                case 1:
                    return new Bandit();
                case 2:
                    return new Dragon();
                case 3:
                    return new Spider();
                default:
                    return new Troll();
            }
        }
    }
}

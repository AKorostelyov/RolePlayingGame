package ru.skillfactory.game;

import ru.skillfactory.creatures.player.Player;
import ru.skillfactory.maintenance.Printer;

/**
 * Класс для сбора статистики
 */
public class Statistic {
    private static int killedMonsters = 0;
    private static int earnedCoins = 0;
    private static int usedHeals = 0;
    private static double spentCoins = 0;

    public static void addMonster() {
        killedMonsters += 1;
    }

    public static void addCoins(int coins) {
        earnedCoins += coins;
    }

    public static void addHeals(int heals) {
        usedHeals += heals;
    }

    public static void addSpentCoins(double coins) {
        spentCoins += coins;
    }

    public static void showStatistic() {
        Printer.formatPrint(GameMessages.STATS_FULL_GAME_STATISTIC_MESSAGE,
                Player.getInstance().getName(),
                Player.getInstance().getPlayerClass(),
                String.valueOf(Player.getInstance().getLvl()),
                String.valueOf(Player.getInstance().getStrengthLvl()),
                String.valueOf(Player.getInstance().getAgilityLvl()),
                String.valueOf(Player.getInstance().getPerceptionLvl()),
                String.valueOf(Player.getInstance().getLuckLvl()),
                String.valueOf(Player.getInstance().getDefence()),
                String.valueOf(killedMonsters),
                String.valueOf(usedHeals),
                String.valueOf(earnedCoins),
                String.valueOf(Math.round(spentCoins))
        );
    }

    public static void resetStatistic() {
        killedMonsters = 0;
        earnedCoins = 0;
        usedHeals = 0;
        spentCoins = 0;
    }
}

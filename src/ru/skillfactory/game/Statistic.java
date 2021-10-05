package ru.skillfactory.game;

import ru.skillfactory.creatures.monsters.*;
import ru.skillfactory.creatures.player.Player;
import ru.skillfactory.maintenance.Printer;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для сбора статистики
 */
public class Statistic {
    private static int killedMonsters = 0;
    private static int earnedCoins = 0;
    private static int usedHeals = 0;
    private static double spentCoins = 0;
    private static Map<String, Integer> specificMonsters = new HashMap<>();

    public static void addMonster(Monster monster) {
        killedMonsters += 1;
        String monsterType = monster.getClass().getSimpleName();
        if (specificMonsters.containsKey(monsterType)) {
            specificMonsters.put(monsterType, specificMonsters.get(monsterType) + 1);
        } else {
            specificMonsters.put(monsterType, 1);
        }
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
                String.valueOf(usedHeals),
                String.valueOf(earnedCoins),
                String.valueOf(Math.round(spentCoins)),
                String.valueOf(killedMonsters),
                String.valueOf((specificMonsters.get(Skeleton.class.getSimpleName()) == null) ? "0" : specificMonsters.get(Skeleton.class.getSimpleName())),
                String.valueOf((specificMonsters.get(Bandit.class.getSimpleName()) == null) ? "0" : specificMonsters.get(Bandit.class.getSimpleName())),
                String.valueOf((specificMonsters.get(Spider.class.getSimpleName()) == null) ? "0" : specificMonsters.get(Spider.class.getSimpleName())),
                String.valueOf((specificMonsters.get(Troll.class.getSimpleName()) == null) ? "0" : specificMonsters.get(Troll.class.getSimpleName())),
                String.valueOf((specificMonsters.get(Dragon.class.getSimpleName()) == null) ? "0" : specificMonsters.get(Dragon.class.getSimpleName()))
        );
    }

    public static void resetStatistic() {
        killedMonsters = 0;
        earnedCoins = 0;
        usedHeals = 0;
        spentCoins = 0;
        specificMonsters.clear();
    }
}

package ru.skillfactory.game;

import ru.skillfactory.battles.BattleHelper;
import ru.skillfactory.creatures.player.Player;
import ru.skillfactory.maintenance.Printer;
import ru.skillfactory.trades.Items;
import ru.skillfactory.trades.TradeShop;

import java.util.Scanner;

/**
 * Основной класс игры
 */
public class Game {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TradeShop tradeShop = new TradeShop();
    public static boolean isGameActive = true;
    private static Location currentLocation = Location.FOREST;
    private static int gameDays = 1;

    /**
     * Начало игры, определяет класс героя и проводит первый шаг
     *
     * @throws InterruptedException
     */
    public static void startGame() throws InterruptedException {
        tradeShop.resetStocks();

        Printer.print(GameMessages.COMMON_NAME_CHOICE_MESSAGE);
        String playerName = scanner.next();
        Printer.formatPrint(GameMessages.COMMON_GREETING_MESSAGE, playerName);
        Printer.print(GameMessages.COMMON_CLASS_DESCRIPTION_AND_CHOICE_MESSAGE);
        int classIndex = scanner.nextInt();
        switch (classIndex) {
            case 1:
                Player.getInstance().setKnightClass(playerName);
                break;
            case 2:
                Player.getInstance().setHunterClass(playerName);
                break;
            default:
                Player.getInstance().setPeasantClass(playerName);
                break;
        }
        Printer.print(GameMessages.COMMON_FIRST_WAY_CHOICE_MESSAGE);
        currentLocation = Location.FOREST;
        battle();
        continueGame();
    }

    /**
     * Основной метод игры, предоставляет игроку варианты путей
     *
     * @throws InterruptedException
     */
    private static void continueGame() throws InterruptedException {
        gameDays++;
        if (gameDays % 15 == 0) {
            tradeShop.resetStocks();
            Printer.print(GameMessages.TRADER_STOCK_RESET);
        }

        switch (currentLocation) {
            case FOREST:
                Printer.print(GameMessages.COMMON_FOREST_CHOICE_MESSAGE);
                switch (scanner.nextInt()) {
                    case 1:
                        battle();
                        break;
                    case 2:
                        trade();
                        break;
                    default:
                        isGameActive = false;
                        Statistic.showStatistic();
                        Printer.print(GameMessages.COMMON_FAREWELL_MESSAGE);
                        break;
                }
                break;
            case TOWN:
                Printer.print(GameMessages.COMMON_CITY_CHOICE_MESSAGE);
                switch (scanner.nextInt()) {
                    case 1:
                        trade();
                        break;
                    case 2:
                        battle();
                        break;
                    default:
                        isGameActive = false;
                        Statistic.showStatistic();
                        Printer.print(GameMessages.COMMON_FAREWELL_MESSAGE);
                        break;
                }
                break;
        }
        if (isGameActive)
            continueGame();
    }

    /**
     * Метод торговли
     */
    private static void trade() {
        currentLocation = Location.TOWN;
        tradeShop.registerVisit();
        String choice = scanner.next();
        String count = scanner.next();
        switch (choice) {
            case "1":
                if (tradeShop.buyItem(Items.HEAL, Integer.parseInt(count))) {
                    Player.getInstance().setMoney(
                            Player.getInstance().getMoney() - tradeShop.getHealPoisonPrice() * Integer.parseInt(count));
                    Statistic.addHeals(Integer.parseInt(count));
                    Statistic.addSpentCoins(tradeShop.getHealPoisonPrice() * Integer.parseInt(count));

                    Player.getInstance().refillHealth(Configuration.HEAL_POISON_REFILL_AMOUNT * Integer.parseInt(count));
                }
                break;
            case "2":
                if (tradeShop.buyItem(Items.ARMOR, Integer.parseInt(count))) {
                    Player.getInstance().setMoney(Player.getInstance().getMoney() - tradeShop.getArmorPrice() * Integer.parseInt(count));
                    Player.getInstance().setDefence(Player.getInstance().getDefence() + Configuration.ARMOR_DEFAULT_DEFENSE_BONUS * Integer.parseInt(count));
                    Statistic.addSpentCoins(tradeShop.getArmorPrice() * Integer.parseInt(count));
                }
                break;
            case "0":
                Printer.print(GameMessages.TRADER_FAREWELL_MESSAGE);
                break;
            default:
                Printer.print(GameMessages.TRADER_UNKNOWN_ITEM_MESSAGE);
                break;
        }

    }

    /**
     * Метод битвы
     *
     * @throws InterruptedException
     */
    private static void battle() throws InterruptedException {
        currentLocation = Location.FOREST;
        Thread battle = new Thread(BattleHelper::initBattle);
        battle.start();
        battle.join();
        if (Player.getInstance().isAlive()) {
            Printer.formatPrint(GameMessages.STATS_HEALTH_MESSAGE,
                    Player.getInstance().getName(),
                    String.valueOf(Player.getInstance().getHealth()),
                    String.valueOf(Player.getInstance().getMaxHealth()));
        } else {
            Printer.print(GameMessages.BATTLE_DEAD_MESSAGE);
            Statistic.showStatistic();
            Printer.print(GameMessages.COMMON_TRY_AGAIN_MESSAGE);
            int decision = scanner.nextInt();
            if (decision == 1) {
                startGame();
            } else {
                isGameActive = false;
                Printer.print(GameMessages.COMMON_FAREWELL_MESSAGE);
            }
        }
    }
}

package ru.skillfactory.game;

import ru.skillfactory.battles.BattleManager;
import ru.skillfactory.creatures.player.Player;
import ru.skillfactory.creatures.player.PlayerClass;
import ru.skillfactory.maintenance.Printer;
import ru.skillfactory.maintenance.SaveManager;
import ru.skillfactory.trades.Items;
import ru.skillfactory.trades.TradeShop;

import java.io.FileNotFoundException;
import java.io.IOException;
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

    public static void startGame() throws InterruptedException {
        Printer.print(GameMessages.COMMON_START_GAME_MESSAGE);
        switch (scanner.nextInt()) {
            case 1:
                loadGame();
                currentLocation = Location.TOWN;
                continueGame();
                break;
            default:
                startNewGame();
                break;
        }
    }

    /**
     * Начало игры, определяет класс героя и проводит первый шаг
     *
     * @throws InterruptedException
     */
    public static void startNewGame() throws InterruptedException {
        resetGame();
        Printer.print(GameMessages.COMMON_NAME_CHOICE_MESSAGE);
        String playerName = scanner.next();
        Printer.formatPrint(GameMessages.COMMON_GREETING_MESSAGE, playerName);
        Printer.formatPrint(GameMessages.COMMON_CLASS_DESCRIPTION_AND_CHOICE_MESSAGE, PlayerClass.getClassDescription());
        int classIndex = scanner.nextInt();
        Player.getInstance().setPlayerClass(playerName, classIndex-1);
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
                        endGame();
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
                        endGame();
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
        if (choice.equals("0")){
            Printer.print(GameMessages.TRADER_FAREWELL_MESSAGE);
            return;
        }
        if (!tradeShop.buyItem(Integer.parseInt(choice), Integer.parseInt(count))) {
            Printer.print(GameMessages.TRADER_UNKNOWN_ITEM_MESSAGE);
        }
    }

    /**
     * Метод битвы
     *
     * @throws InterruptedException
     */
    private static void battle() throws InterruptedException {
        currentLocation = Location.FOREST;
        Thread battle = new Thread(BattleManager::initBattle);
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
            switch (decision) {
                case 1:
                    try {
                        SaveManager.loadAutoSave();
                    } catch (IOException e) {
                        Printer.formatPrint(GameMessages.COMMON_ERROR_MESSAGE, e.getMessage());
                    }
                    break;
                case 2:
                    startNewGame();
                    break;
                default:
                    isGameActive = false;
                    Printer.print(GameMessages.COMMON_FAREWELL_MESSAGE);
                    if (!SaveManager.clearAutoSaves()) {
                        Printer.formatPrint(GameMessages.COMMON_ERROR_MESSAGE, "There is problems with autosaves, it can influence on next session. Be caution!");
                    }
                    break;
            }
        }
    }

    /**
     * Процесс завершения игры
     */
    private static void endGame() {
        isGameActive = false;
        Statistic.showStatistic();
        Printer.print(GameMessages.COMMON_SAVE_GAME_DIALOG_MESSAGE);
        if (scanner.nextInt() == 1) {
            saveGame();
        }
        if (!SaveManager.clearAutoSaves()) {
            Printer.formatPrint(GameMessages.COMMON_ERROR_MESSAGE, "There is problems with autosaves, it can influence on next session. Be caution!");
        }
        Printer.print(GameMessages.COMMON_FAREWELL_MESSAGE);
    }

    /**
     * Сохранение игры
     */
    private static void saveGame() {
        try {
            SaveManager.saveGame(false);
        } catch (IOException e) {
            Printer.formatPrint(GameMessages.COMMON_ERROR_MESSAGE, e.getMessage());
        }

    }

    /**
     * Загрузка сохраненной игры
     */
    private static void loadGame() {
        String saves = SaveManager.getSavesList();
        String[] saveList;
        if (saves != null)
            saveList = saves.split("\n");
        else {
            Printer.formatPrint(GameMessages.COMMON_ERROR_MESSAGE, "No saves found");
            return;
        }
        StringBuilder saveMessage = new StringBuilder();
        for (int i = 0; i < saveList.length; i++) {
            saveMessage.append(i + 1).append(". ").append(saveList[i]).append("\n");
        }
        Printer.formatPrint(GameMessages.COMMON_SAVE_CHOOSING_DIALOG, saveMessage.toString());
        int save = scanner.nextInt();
        try {
            SaveManager.loadGame(Configuration.PATH_TO_SAVES + saveList[save - 1]);
        } catch (FileNotFoundException e) {
            Printer.formatPrint(GameMessages.COMMON_ERROR_MESSAGE, e.getMessage());
        }

    }

    /**
     * Сброс игры
     */
    private static void resetGame() {
        tradeShop.resetStocks();
        Statistic.resetStatistic();
        gameDays = 1;
    }
}

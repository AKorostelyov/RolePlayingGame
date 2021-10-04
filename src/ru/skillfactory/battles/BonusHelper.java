package ru.skillfactory.battles;

import ru.skillfactory.creatures.player.Player;
import ru.skillfactory.game.GameMessages;
import ru.skillfactory.game.Statistic;
import ru.skillfactory.maintenance.Printer;

import java.util.Random;

/**
 * Класс рассчитывающий награды за победы в бою
 */
public class BonusHelper {

    /**
     * Метод, рассчитывающий количество монет за монстра
     */
    public static void earnCoins() {
        Random random = new Random();
        int coins = random.nextInt(11) * Player.getInstance().getLuckLvl();
        Player.getInstance().setMoney(Player.getInstance().getMoney() + coins);
        Statistic.addCoins(coins);
        Printer.formatPrint(GameMessages.BATTLE_PLAYER_EARN_GOLD_MESSAGE, Player.getInstance().getName(), String.valueOf(coins));
    }
}

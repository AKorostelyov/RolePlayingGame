package ru.skillfactory.battles;

import ru.skillfactory.creatures.monsters.Monster;
import ru.skillfactory.creatures.player.Player;
import ru.skillfactory.game.GameMessages;
import ru.skillfactory.game.Statistic;
import ru.skillfactory.maintenance.Printer;

import java.util.Random;

/**
 * Класс рассчитывающий награды за победы в бою
 */
public class BonusCalculator {

    /**
     * Метод, рассчитывающий количество монет за монстра
     */
    public static void earnCoins(Monster monster) {
        Random random = new Random();
        int coins = random.nextInt(monster.getGoldBonus()*Player.getInstance().getLvl()) * Player.getInstance().getLuckLvl();
        Player.getInstance().setMoney(Player.getInstance().getMoney() + coins);
        if (coins != 0) {
            Statistic.addCoins(coins);
            Printer.formatPrint(GameMessages.BATTLE_PLAYER_EARN_GOLD_MESSAGE,
                    Player.getInstance().getName(),
                    String.valueOf(coins));
        } else {
            Printer.formatPrint(GameMessages.BATTLE_PLAYER_HAVE_NOTHING_MESSAGE,
                    Player.getInstance().getName(),
                    monster.getClass().getSimpleName());
        }
    }
}

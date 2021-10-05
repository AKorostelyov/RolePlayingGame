package ru.skillfactory.battles;

import ru.skillfactory.creatures.player.Player;
import ru.skillfactory.game.GameMessages;
import ru.skillfactory.maintenance.Printer;

import java.util.Random;

/**
 * Класс, вычисляющий наносимый урон
 */
public class DamageHelper {
    static final Random random = new Random();

    /**
     * Расчет вероятности критического удара
     *
     * @return критический/не критический урон
     */
    public static boolean isCritical() {
        boolean isCritical = random.nextInt(5) == 4;
        if (isCritical) {
            Printer.print(GameMessages.BATTLE_CRITICAL_HIT_MESSAGE);
        }
        return isCritical;
    }

    /**
     * Расчет вероятности критического удара с учетом показателя удачи
     *
     * @param luck удача
     * @return критический/не критический урон
     */
    public static boolean isCritical(int luck) {
        boolean isCritical = random.nextInt(11 - luck) == 11 - luck - 1;
        if (isCritical) {
            Printer.print(GameMessages.BATTLE_CRITICAL_HIT_MESSAGE);
            Player.getInstance().setExperienceToLvl(Player.getInstance().getExperienceToLvl() - 1);
        }
        return isCritical;
    }

    /**
     * Вычисление нанесенного монстром урона, на основе ловкости и силы
     *
     * @param baseDamage базовый урон
     * @param agilityLvl уровень ловкости
     * @return нанесенный урон
     */
    public static int calculateMonsterDamage(int baseDamage, int agilityLvl) {
        if (random.nextInt(11) > agilityLvl) {
            return 0;
        } else {
            return (isCritical()) ? baseDamage * 3 : baseDamage;
        }
    }

    /**
     * Вычисление нанесенного персонажем урона, на основе ловкости, силы и удачи
     *
     * @return нанесенный урон
     */
    public static int calculatePlayerDamage() {
        if (random.nextInt(11) > Player.getInstance().getAgilityLvl()) {
            return 0;
        } else {
            return (isCritical(Player.getInstance().getLuckLvl())) ? Player.getInstance().getStrengthLvl() * 3 : Player.getInstance().getStrengthLvl();
        }
    }
}

package ru.skillfactory.battles;

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
     * Вычисление нанесенного урона, на основе ловкости
     *
     * @param baseDamage базовый урон
     * @param agilityLvl уровень ловкости
     * @return нанесенный урон
     */
    public static int calculateDamage(int baseDamage, int agilityLvl) {
        if (random.nextInt(11) > agilityLvl) {
            return 0;
        } else {
            return (isCritical()) ? baseDamage * 3: baseDamage;
        }
    }
}

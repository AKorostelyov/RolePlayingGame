package ru.skillfactory.battles;

import javafx.util.Pair;
import ru.skillfactory.creatures.player.Player;
import ru.skillfactory.game.Configuration;
import ru.skillfactory.game.GameMessages;
import ru.skillfactory.maintenance.Printer;

/**
 * Класс, рассчитывающий полученный опыт и повышение уровня героя
 */
public class ExperienceHelper {

    /**
     * Начисление полученного опыта герою
     *
     * @param expPoints полученный опыт
     */
    public static void updateExpPlayer(int expPoints) {
        if (expPoints < Player.getInstance().getExperienceToLvl()) {
            Player.getInstance().setExperienceToLvl(Player.getInstance().getExperienceToLvl() - expPoints);
            Printer.formatPrint(GameMessages.BATTLE_PLAYER_EARNED_EXPERIENCE_MESSAGE,
                    Player.getInstance().getName(),
                    String.valueOf(expPoints),
                    String.valueOf(Player.getInstance().getExperienceToLvl()),
                    String.valueOf(Player.getInstance().getLvl() + 1));
        } else {
            Pair<Integer, Integer> newPlayerLevelData = getNewLvl(
                    Player.getInstance().getLvl(),
                    Player.getInstance().getExperienceToLvl(),
                    expPoints);

            Player.getInstance().setLvl(newPlayerLevelData.getKey());
            Player.getInstance().setExperienceToLvl(newPlayerLevelData.getValue());
            Player.getInstance().setMaxHealth(Player.getInstance().getMaxHealth() + Player.getInstance().getLvl() * 2);
            Player.getInstance().setStrengthLvl(Player.getInstance().getStrengthLvl() + Player.getInstance().getLvl());
            Player.getInstance().fullFillHealth();
            Printer.formatPrint(GameMessages.BATTLE_PLAYER_LEVEL_UP_MESSAGE,
                    Player.getInstance().getName(),
                    String.valueOf(Player.getInstance().getLvl()),
                    String.valueOf(Player.getInstance().getExperienceToLvl()),
                    String.valueOf(Player.getInstance().getLvl() + 1));
        }
    }

    /**
     * Расчет нового уровня героя
     *
     * @param lvl       текущий уровень героя
     * @param toLvl     требуемый опыт до следующего уровня
     * @param expPoints полученный опыт
     * @return новый уровень и остаток до следующего уровня
     */
    private static Pair<Integer, Integer> getNewLvl(int lvl, int toLvl, int expPoints) {
        int newExpPoints = expPoints - toLvl;
        int newLvl = lvl + 1;
        while (newExpPoints >= Configuration.EXP_POINTS_TO_LVL) {
            newLvl++;
            newExpPoints -= Configuration.EXP_POINTS_TO_LVL;
        }
        return new Pair<>(newLvl, Configuration.EXP_POINTS_TO_LVL - newExpPoints);
    }
}

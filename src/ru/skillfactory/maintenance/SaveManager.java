package ru.skillfactory.maintenance;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.skillfactory.creatures.player.Player;
import ru.skillfactory.game.Configuration;
import ru.skillfactory.game.GameMessages;
import ru.skillfactory.game.Statistic;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс управления сохранениями
 */
public class SaveManager {

    /**
     * Сохранение игры
     *
     * @param isAuto автосохранение/сохранение (от этого зависит путь создаваемого файла)
     * @throws IOException путь не найден
     */
    public static void saveGame(boolean isAuto) throws IOException {
        JsonObject game = new JsonObject();
        game.add("character", saveCharacter());
        game.add("statistic", saveStatistic());
        String filePath = ((isAuto) ? Configuration.PATH_TO_AUTOSAVES : Configuration.PATH_TO_SAVES) + Player.getInstance().getPlayerClass() + "_" + Player.getInstance().getName() + ((isAuto) ? LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) : "") + ".json";
        File file = new File(filePath);
        Writer writer;
        if (file.createNewFile()) {
            writer = new FileWriter(filePath);
            writer.write(game.toString());
        } else {
            writer = new FileWriter(filePath);
            writer.flush();
            writer.write(game.toString());
        }
        writer.close();
        Printer.print(GameMessages.COMMON_GAME_SAVED_MESSAGE);
    }

    /**
     * Формирует данные персонажа для сохранения
     *
     * @return данные персонажа
     */
    private static JsonObject saveCharacter() {
        JsonObject character = new JsonObject();
        character.addProperty("name", Player.getInstance().getName());
        character.addProperty("lvl", Player.getInstance().getLvl());
        character.addProperty("class", Player.getInstance().getPlayerClass());
        character.addProperty("strength", Player.getInstance().getStrengthLvl());
        character.addProperty("agility", Player.getInstance().getAgilityLvl());
        character.addProperty("perception", Player.getInstance().getPerceptionLvl());
        character.addProperty("luck", Player.getInstance().getLuckLvl());
        character.addProperty("defence", Player.getInstance().getDefence());
        character.addProperty("maxHealth", Player.getInstance().getMaxHealth());
        character.addProperty("health", Player.getInstance().getHealth());
        character.addProperty("money", Player.getInstance().getMoney());
        character.addProperty("expToLvl", Player.getInstance().getExperienceToLvl());
        return character;
    }

    /**
     * Формирует данные статистики для сохранения
     *
     * @return данные статистики
     */
    private static JsonObject saveStatistic() {
        JsonObject statistic = new JsonObject();
        statistic.addProperty("earnedCoins", Statistic.getEarnedCoins());
        statistic.addProperty("spentCoins", Statistic.getSpentCoins());
        statistic.addProperty("healsUsed", Statistic.getUsedHeals());
        statistic.addProperty("killedMonsters", Statistic.getKilledMonsters());
        JsonObject monsters = new JsonObject();
        for (Map.Entry<String, Integer> monster : Statistic.getSpecificMonsters().entrySet()) {
            monsters.addProperty(monster.getKey(), monster.getValue());
        }
        statistic.add("monsters", monsters);

        return statistic;
    }

    /**
     * Получение списка файлов сохранения для вывода на экран
     *
     * @return список файлов
     */
    public static String getSavesList() {
        File saves_dir = new File(Configuration.PATH_TO_SAVES);
        File[] arrFiles = saves_dir.listFiles();
        StringBuilder savesList = new StringBuilder();
        if (arrFiles != null) {
            for (File save : arrFiles) {
                savesList.append(save.getName()).append("\n");
            }
            return savesList.substring(0, savesList.length() - 1);
        } else return null;
    }

    /**
     * Загрузка последнего автосохранения
     *
     * @throws IOException файл не найден
     */
    public static void loadAutoSave() throws IOException {
        File saves_dir = new File(Configuration.PATH_TO_AUTOSAVES);
        File[] arrFiles = saves_dir.listFiles();
        File latestSave = arrFiles[0];
        for (File file : arrFiles) {
            if (file.lastModified() > latestSave.lastModified()) {
                latestSave = file;
            }
        }
        loadGame(latestSave.getPath());
    }


    /**
     * Загрузка файла сохранения в игру
     *
     * @param filePath путь к файлу
     * @throws FileNotFoundException
     */
    public static void loadGame(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        StringBuilder gameSaveContent = new StringBuilder();
        while (scanner.hasNext()) {
            gameSaveContent.append(scanner.nextLine());
        }
        scanner.close();
        JsonObject gameSave = JsonParser.parseString(gameSaveContent.toString()).getAsJsonObject();
        JsonObject character = gameSave.getAsJsonObject("character");
        JsonObject statistic = gameSave.getAsJsonObject("statistic");

        Player.getInstance().setName(character.get("name").getAsString());
        Player.getInstance().setPlayerClass(character.get("class").getAsString());
        Player.getInstance().setLvl(character.get("lvl").getAsInt());
        Player.getInstance().setAgilityLvl(character.get("agility").getAsInt());
        Player.getInstance().setPerceptionLvl(character.get("perception").getAsInt());
        Player.getInstance().setLuckLvl(character.get("luck").getAsInt());
        Player.getInstance().setDefence(character.get("defence").getAsInt());
        Player.getInstance().setStrengthLvl(character.get("strength").getAsInt());
        Player.getInstance().setHealth(character.get("health").getAsInt());
        Player.getInstance().setMaxHealth(character.get("maxHealth").getAsInt());
        Player.getInstance().setMoney(character.get("money").getAsInt());
        Player.getInstance().setExperienceToLvl(character.get("expToLvl").getAsInt());
        Player.getInstance().setAlive(true);

        Map<String, Integer> monsters = new HashMap<>();
        for (String monster : statistic.getAsJsonObject("monsters").keySet()) {
            monsters.put(monster, statistic.getAsJsonObject("monsters").get(monster).getAsInt());
        }

        Statistic.loadStatistic(
                statistic.get("killedMonsters").getAsInt(),
                statistic.get("earnedCoins").getAsInt(),
                statistic.get("healsUsed").getAsInt(),
                statistic.get("spentCoins").getAsInt(),
                monsters);
        Statistic.showStatistic();
    }

    public static boolean clearAutoSaves() {
        File autosaves = new File(Configuration.PATH_TO_AUTOSAVES);
        boolean status = false;
        for (File file : autosaves.listFiles()) {
            status = file.delete();
        }
        return status;
    }
}

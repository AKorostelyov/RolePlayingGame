package ru.skillfactory.maintenance;

import ru.skillfactory.game.GameMessages;

import java.time.LocalTime;

/**
 * Класс вывода информации в консоль
 */
public class Printer {

    public static void print(GameMessages st) {

        printMsg(st.toString());
    }

    public static void formatPrint(GameMessages template, String... parameters) {

        printMsg(String.format(template.toString(), parameters));
    }

    private static void printMsg(String msg) {
        System.out.println("[" + LocalTime.now() + "]: " + msg);
    }
}

package ru.skillfactory.maintenance;

import ru.skillfactory.game.GameMessages;

import java.time.LocalTime;

/**
 * Класс вывода информации в консоль
 */
public class Printer {

    /**
     * Вывод сообщения без изменений
     *
     * @param st сообщение
     */
    public static void print(GameMessages st) {

        printMsg(st.toString());
    }

    /**
     * Заполнение и вывод шаблона сообщения
     *
     * @param template   шаблон
     * @param parameters параметры для шаблона
     */
    public static void formatPrint(GameMessages template, String... parameters) {

        printMsg(String.format(template.toString(), parameters));
    }

    /**
     * Приведение сообщений к общему формату
     *
     * @param msg сообщение
     */
    private static void printMsg(String msg) {
        System.out.println("[" + LocalTime.now() + "]: " + msg);
    }
}

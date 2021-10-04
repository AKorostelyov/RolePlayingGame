package ru.skillfactory;

import ru.skillfactory.game.Game;
import ru.skillfactory.game.GameMessages;
import ru.skillfactory.maintenance.Printer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Printer.print(GameMessages.COMMON_WELCOME_MESSAGE);
        Game.startGame();
    }
}

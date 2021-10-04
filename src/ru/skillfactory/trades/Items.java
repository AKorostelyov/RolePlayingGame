package ru.skillfactory.trades;

/**
 * Перечисление ассортимента торговца
 */
public enum Items {
    HEAL("heal"),

    ARMOR("armor");


    private final String name;

    Items(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

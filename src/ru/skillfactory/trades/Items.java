package ru.skillfactory.trades;

/**
 * Перечисление ассортимента торговца
 */
public enum Items {
    HEAL("heal"),
    AGILITY_BOOSTER("agility"),
    PERCEPTION_BOOSTER("perception"),
    LUCK_BOOSTER("luck"),
    ARMOR("armor"),
    WEAPON("weapon");


    private final String name;

    Items(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

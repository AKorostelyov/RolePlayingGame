package ru.skillfactory.trades;

/**
 * Перечисление ассортимента торговца
 */
public enum Items {
    HEAL("Heal poison (+5 health points)"),
    AGILITY_BOOSTER("Agility booster (+1 to agility level)"),
    PERCEPTION_BOOSTER("Perception booster (+1 to perception level)"),
    LUCK_BOOSTER("Luck booster (+1 to luck level)"),
    ARMOR("Armor (+5 to defence)"),
    WEAPON("Weapon upgrade (+5 to damage)");


    private final String description;

    Items(String description) {
        this.description = description;
    }

    public String getdescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}

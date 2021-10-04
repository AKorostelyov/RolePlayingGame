package ru.skillfactory.game;

/**
 * Перечисление локаций игры
 */
public enum Location {
    FOREST("forest"),
    TOWN("town");

    private final String locationName;

    Location(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }
}

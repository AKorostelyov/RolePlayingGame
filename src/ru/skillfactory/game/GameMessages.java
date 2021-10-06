package ru.skillfactory.game;

/**
 * Класс, содержащий все выводимые игрой сообщения
 */
public enum GameMessages {

    COMMON_WELCOME_MESSAGE("Welcome traveller!\n" +
            "Let`s hunt some monsters"),
    COMMON_START_GAME_MESSAGE("Do you have saved character?\n" +
            " 1. Yes, I want load it,\n" +
            " 2. No, I want start new game."),
    COMMON_SAVE_CHOOSING_DIALOG("Which character do you want to load?\n %s"),
    COMMON_NAME_CHOICE_MESSAGE("First, name your character\n" +
            "Name:"),
    COMMON_GREETING_MESSAGE("Perfect! Nice to meet you, %s"),
    COMMON_CLASS_DESCRIPTION_AND_CHOICE_MESSAGE("Now let`s choose your class\n" +
            "1. Knight: strong warrior, but a little slow\n" +
            "2. Hunter: fast unit, who can avoid monsters attacks\n" +
            "3. Wizard: weak unit, with amazing damage\n" +
            "4. Peasant: HARDCORE\n" +
            "So, what you choose? (enter number of class)"),
    COMMON_FIRST_WAY_CHOICE_MESSAGE("Now you`re ready for adventures\n" +
            "Let`s go to a first battle"),
    COMMON_FOREST_CHOICE_MESSAGE("Where are we go now?\n" +
            " 1. Continue hunt in forest\n" +
            " 2. Shopping in the city\n" +
            " 3. Exit game"),
    COMMON_CITY_CHOICE_MESSAGE("Where are we go now?\n" +
            " 1. Shop something more\n" +
            " 2. Hunting in forest\n" +
            " 3. Exit game"),
    COMMON_FAREWELL_MESSAGE("Goodbye! Hope you like this game!"),
    COMMON_TRY_AGAIN_MESSAGE("Load last save?\n" +
            " 1. Yes\n" +
            " 2. No, start new game,\n" +
            " 3. Exit"),
    COMMON_CHARACTER_DESCRIPTION_MESSAGE("Here`s your character:\n" +
            " Health Points: %s,\n" +
            " Money: %s coins,\n" +
            " Agility: %s,\n" +
            " Perception: %s,\n" +
            " Strength: %s\n" +
            " Luck: %s"),
    COMMON_SAVE_GAME_DIALOG_MESSAGE("Do you want to save this game?\n" +
            " 1. Yes,\n" +
            " 2. No."),
    COMMON_GAME_SAVED_MESSAGE("Game successfully saved!"),
    COMMON_ERROR_MESSAGE("Something went wrong: %s"),

    MONSTER_SKELETON_MESSAGE("On your road appears Skeleton. Lets take some fun."),
    MONSTER_BANDIT_MESSAGE("On your road appears Bandit. Lets the battle begin."),
    MONSTER_SPIDER_MESSAGE("On your road appears Spider. Be careful, it can be painful."),
    MONSTER_TROLL_MESSAGE("On your road appears Troll. If it strike, bones are cracked"),
    MONSTER_DRAGON_MESSAGE("On your road appears Dragon. Maybe you should run away?"),

    TRADER_WELCOME_MESSAGE("Welcome, traveller!\n" +
            " Here`s my assortment:\n%s\n" +
            "For choice enter item number and count.\n" +
            "For exit enter '0 0'"),
    TRADER_UNKNOWN_ITEM_MESSAGE("I don`t know what do you want from me!"),
    TRADER_NOT_ENOUGH_MONEY_MESSAGE("No money, no honey, dear"),
    TRADER_AFFORD_ITEMS_COUNT_MESSAGE("You can buy only %s pieces."),
    TRADER_NO_STOCKS_MESSAGE("I don`t have this item for now. Come later."),
    TRADER_ITEMS_PRICE_MESSAGE("Good! It would cost you %s coins."),
    TRADER_FAREWELL_MESSAGE("I`m glad to deal with you!\n" +
            "Goodbye! Good hunting!"),
    TRADER_NOT_ENOUGH_STOCK_MESSAGE("Not enough stocks. I can offer only %s pieces."),
    TRADER_PLAYERS_COINS("%s has %s coins in pockets"),
    TRADER_STOCK_RESET("Trader`s stock was reset"),

    PLAYER_HEALTH_FULFILLED_MESSAGE("%s's health fulfilled"),
    PLAYER_HEALTH_REDEEMED_MESSAGE("%s redeem %s hp"),
    PLAYER_DEFENCE_UPGRADED_MESSAGE("%s upgrades defence on %s points, current defence - %s"),
    PLAYER_AGILITY_UPGRADED_MESSAGE("%s upgrades agility on %s points, current agility - %s"),
    PLAYER_LUCK_UPGRADED_MESSAGE("%s upgrades luck on %s points, current luck - %s"),
    PLAYER_PERCEPTION_UPGRADED_MESSAGE("%s upgrades perception on %s points, current perception - %s"),
    PLAYER_DAMAGE_UPGRADED_MESSAGE("%s upgrades damage on %s points, current damage - %s"),


    STATS_HEALTH_MESSAGE("%s has %s / %s health, for now"),
    STATS_FULL_GAME_STATISTIC_MESSAGE(
            "%s statistic:\n" +
                    " Class: %s,\n" +
                    " Level: %s,\n" +
                    " Abilities:\n" +
                    "   Strength: %s,\n" +
                    "   Agility: %s,\n" +
                    "   Perception: %s,\n" +
                    "   Luck: %s,\n" +
                    "   Defence: %s\n" +
                    " Items:\n" +
                    "   Heals used: %s,\n" +
                    "   Coins earned: %s\n" +
                    "   Spent coins: %s\n" +
                    " Monsters:\n" +
                    "   All: %s,\n" +
                    "   Skeletons: %s,\n" +
                    "   Bandits: %s,\n" +
                    "   Spiders: %s,\n" +
                    "   Trolls: %s,\n" +
                    "   Dragons: %s."),

    BATTLE_MONSTER_HIT_MESSAGE("Monster %s gets %s points of damage"),
    BATTLE_PLAYER_HIT_MESSAGE("%s gets %s points of damage"),
    BATTLE_PLAYER_IGNORED_DAMAGE_MESSAGE("%s ignored %s damage"),
    BATTLE_MONSTER_KILL_MESSAGE("Monster %s was killed. %s remaining health: %s"),
    BATTLE_PLAYER_EARNED_EXPERIENCE_MESSAGE("%s earned %s exp points. %s points left to %s level"),
    BATTLE_PLAYER_LEVEL_UP_MESSAGE("%s up to %s level. %s points left to %s level"),
    BATTLE_CRITICAL_HIT_MESSAGE("IT`S CRITICAL HIT"),
    BATTLE_MISSED_HIT("%s is missed"),
    BATTLE_PLAYER_EARN_GOLD_MESSAGE("%s earned %s coins"),
    BATTLE_PLAYER_HAVE_NOTHING_MESSAGE("%s have nothing found in %s pockets"),
    BATTLE_DEAD_MESSAGE(
            "\n||||      ||||      |||||      ||||     |||| |||| |||||||||||||  |||||||||||||       |||||||||||    ||||  |||||||||||||  |||||||||||  \n" +
                    "||||      ||||  ||||     ||||  ||||     |||| ||   ||||     ||||  |||||||||||||       ||||      |||  ||||  |||||||||||||  ||||      |||\n" +
                    "  ||||  ||||    ||||     ||||  ||||     |||| ||   ||||     ||||  ||||                ||||      |||  ||||  ||||           ||||      |||\n" +
                    "  ||||  ||||    ||||     ||||  ||||     ||||      ||||     ||||  ||||||||||          ||||      |||  ||||  ||||||||||     ||||      |||\n" +
                    "     ||||       ||||     ||||  ||||     ||||      ||||||||||     ||||||||||          ||||      |||  ||||  ||||||||||     ||||      |||\n" +
                    "     ||||       ||||     ||||  ||||     ||||      |||| ||||||    ||||                ||||      |||  ||||  ||||           ||||      |||\n" +
                    "     ||||       ||||     ||||  ||||     ||||      ||||  ||||||   |||||||||||||       ||||      |||  ||||  |||||||||||||  ||||      |||\n" +
                    "     ||||           |||||          |||||          ||||   ||||||  |||||||||||||       |||||||||||    ||||  |||||||||||||  |||||||||||  ");


    private final String message;

    GameMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    public String getMessage() {
        return message;
    }
}

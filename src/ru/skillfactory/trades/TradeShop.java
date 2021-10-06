package ru.skillfactory.trades;

import ru.skillfactory.creatures.player.Player;
import ru.skillfactory.game.Configuration;
import ru.skillfactory.game.GameMessages;
import ru.skillfactory.game.Statistic;
import ru.skillfactory.maintenance.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс, описывающий работу торговца
 */
public class TradeShop {
    private List<Assortment> assortments;

    public TradeShop() {
        initAssortment();
    }

    private void initAssortment() {
        assortments = new ArrayList<>();
        this.assortments.add(new Assortment(Items.HEAL, Configuration.HEAL_POISON_DEFAULT_STOCK, Configuration.HEAL_POISON_DEFAULT_PRICE));
        this.assortments.add(new Assortment(Items.ARMOR, Configuration.ARMOR_DEFAULT_STOCK, Configuration.ARMOR_DEFAULT_PRICE));
        this.assortments.add(new Assortment(Items.AGILITY_BOOSTER, Configuration.AGILITY_BOOSTER_DEFAULT_STOCK, Configuration.AGILITY_BOOSTER_DEFAULT_PRICE));
        this.assortments.add(new Assortment(Items.LUCK_BOOSTER, Configuration.LUCK_BOOSTER_DEFAULT_STOCK, Configuration.LUCK_BOOSTER_DEFAULT_PRICE));
        this.assortments.add(new Assortment(Items.PERCEPTION_BOOSTER, Configuration.PERCEPTION_BOOSTER_DEFAULT_STOCK, Configuration.PERCEPTION_BOOSTER_DEFAULT_PRICE));
        this.assortments.add(new Assortment(Items.WEAPON, Configuration.WEAPON_DEFAULT_STOCK, Configuration.WEAPON_DEFAULT_PRICE));
    }

    /**
     * Обновляет цены, изменяя их случайным образом в отрезке (-15, 15) процентов от базовой цены
     */
    public void updatePrices() {
        double inflation = new Random().nextInt(5) / 100;
        for (Assortment assortment : assortments) {
            assortment.setPrice(Math.round(assortment.getPrice() + (assortment.getPrice() * Player.getInstance().getLvl()) / 20 + ((new Random().nextBoolean()) ? (-1) * (inflation * assortment.getPrice()) : (inflation * assortment.getPrice()))));
        }
    }


    /**
     * Покупка предметов
     *
     * @param itemNum  номер желаемого предмета
     * @param count количество
     * @return успешность операции
     */
    public boolean buyItem(int itemNum, int count) {
        if (itemNum - 1 > assortments.size()) {
            return false;
        }
        int remainStock = trade(count, getItemPrice(itemNum - 1), getItemStock(itemNum - 1));
        if (remainStock < 0) {
            return false;
        } else {
            updateItemStock(itemNum - 1, remainStock);
            switch (assortments.get(itemNum - 1).getItem()) {
                case ARMOR:
                    Player.getInstance().upgradeDefence(Configuration.ARMOR_DEFAULT_DEFENSE_BONUS * count);
                    break;
                case HEAL:
                    Player.getInstance().refillHealth(Configuration.HEAL_POISON_REFILL_AMOUNT * count);
                    Statistic.addHeals(count);
                    break;
                case WEAPON:
                    Player.getInstance().updgradeWeapon(Configuration.WEAPON_DEFAULT_DAMAGE_BONUS * count);
                    break;
                case LUCK_BOOSTER:
                    Player.getInstance().upgradeLuck(Configuration.LUCK_BOOSTEER_DEFAULT_BONUS * count);
                    break;
                case AGILITY_BOOSTER:
                    Player.getInstance().upgradeAgility(Configuration.AGILITY_BOOSTEER_DEFAULT_BONUS * count);
                    break;
                case PERCEPTION_BOOSTER:
                    Player.getInstance().upgradePerception(Configuration.PERCEPTION_BOOSTEER_DEFAULT_BONUS * count);
                    break;
            }
            Statistic.addSpentCoins(assortments.get(itemNum - 1).getPrice() * count);
            return true;
        }
    }

    /**
     * Проведение операции, в зависимости от количества денег у игрока и остатков у торговца
     *
     * @param count запрашиваемое количество предметов
     * @param price цена одного предмета
     * @param stock остаток предмета
     * @return новое значение остатков предмета
     */
    private int trade(int count, double price, int stock) {
        if (stock < 0 || price == 0) {
            Printer.print(GameMessages.TRADER_UNKNOWN_ITEM_MESSAGE);
            return -1;
        }
        if (Player.getInstance().getMoney() < price * count) {
            Printer.print(GameMessages.TRADER_NOT_ENOUGH_MONEY_MESSAGE);
            if (count > 1) {
                Printer.formatPrint(GameMessages.TRADER_AFFORD_ITEMS_COUNT_MESSAGE,
                        String.valueOf(Math.round(Player.getInstance().getMoney() / price))
                );
            }
            return -1;
        }
        if (stock == 0) {
            Printer.print(GameMessages.TRADER_NO_STOCKS_MESSAGE);
            return -1;
        }
        if (count <= stock) {
            Printer.formatPrint(GameMessages.TRADER_ITEMS_PRICE_MESSAGE,
                    String.valueOf(count * price));
            stock = stock - count;
            Printer.print(GameMessages.TRADER_FAREWELL_MESSAGE);
            Player.getInstance().setMoney(
                    Player.getInstance().getMoney() - price * count);
            return stock;
        } else {
            Printer.formatPrint(GameMessages.TRADER_NOT_ENOUGH_STOCK_MESSAGE,
                    String.valueOf(stock));
            return -1;
        }
    }

    /**
     * Учет визита игрока (каждый четвертый визит обновляются остатки предметов)
     * Каждый визит меняются цены
     */
    public void registerVisit() {
        updatePrices();
        Printer.formatPrint(GameMessages.TRADER_PLAYERS_COINS,
                Player.getInstance().getName(),
                String.valueOf(Player.getInstance().getMoney()));
        Printer.formatPrint(GameMessages.TRADER_WELCOME_MESSAGE,
                getAssortment()
        );
    }

    /**
     * Сброс остатков предметов
     */
    public void resetStocks() {
        initAssortment();
    }

    public long getItemPrice(int itemNum) {
        return this.assortments.get(itemNum).getPrice();
    }

    private int getItemStock(int itemNum) {
        return this.assortments.get(itemNum).getStock();
    }

    private void updateItemStock(int itemNum, int stock) {
        assortments.get(itemNum).setStock(stock);
    }

    private Assortment getAssortment(Items items) {
        for (Assortment assortment : assortments) {
            if (assortment.getItem().equals(items)) {
                return assortment;
            }
        }
        return null;
    }

    public String getAssortment() {
        StringBuilder assortmentText = new StringBuilder();
        for (int i = 0; i < this.assortments.size(); i++) {
            assortmentText.append(" ")
                    .append(i + 1)
                    .append(". ")
                    .append(assortments.get(i).getItem())
                    .append(", ")
                    .append(assortments.get(i).getStock())
                    .append(" pcs - ")
                    .append(assortments.get(i).getPrice())
                    .append(" coins\n");
        }
        return assortmentText.toString();
    }
}

class Assortment {
    private Items item;
    private int stock;
    private long price;

    public Assortment(Items item, int stock, int price) {
        this.item = item;
        this.stock = stock;
        this.price = price;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}

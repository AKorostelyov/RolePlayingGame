package ru.skillfactory.trades;

import ru.skillfactory.creatures.player.Player;
import ru.skillfactory.game.Configuration;
import ru.skillfactory.game.GameMessages;
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
    }

    /**
     * Обновляет цены, изменяя их случайным образом в отрезке (-15, 15) процентов от базовой цены
     */
    public void updatePrices() {
        int inflation = Math.round(new Random().nextInt(15) * 25 / 100);
        for (Assortment assortment : assortments) {
            assortment.setPrice(assortment.getPrice() + ((new Random().nextBoolean()) ? (-1) * inflation : inflation));
        }
    }


    /**
     * Покупка предметов
     *
     * @param item  желаемый предмет
     * @param count количество
     * @return успешность операции
     */
    public boolean buyItem(Items item, int count) {
        int remainStock = trade(count, getItemPrice(item), getItemStock(item));
        if (remainStock < 0) {
            return false;
        } else {
            updateItemStock(item, remainStock);
            return true;
        }

//        switch (item) {
//            case HEAL:
//                remainStock = trade(
//                        count,
//                        Player.getInstance().getMoney(),
//                        healPoisonPrice,
//                        healPoisonStock);
//                if (remainStock < 0) {
//                    return false;
//                } else {
//                    healPoisonStock = remainStock;
//                    return true;
//                }
//            case ARMOR:
//                remainStock = trade(
//                        count,
//                        Player.getInstance().getMoney(),
//                        armorPrice,
//                        armorStock);
//                if (remainStock < 0) {
//                    return false;
//                } else {
//                    armorStock = remainStock;
//                    return true;
//                }
//            default:
//                Printer.print(GameMessages.TRADER_UNKNOWN_ITEM_MESSAGE);
//                return false;
//        }
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
                String.valueOf(getItemStock(Items.HEAL)),
                String.valueOf(getItemPrice(Items.HEAL)),
                String.valueOf(getItemStock(Items.ARMOR)),
                String.valueOf(getItemPrice(Items.ARMOR)),
                String.valueOf(getItemStock(Items.AGILITY_BOOSTER)),
                String.valueOf(getItemPrice(Items.AGILITY_BOOSTER)),
                String.valueOf(getItemStock(Items.LUCK_BOOSTER)),
                String.valueOf(getItemPrice(Items.LUCK_BOOSTER)),
                String.valueOf(getItemStock(Items.PERCEPTION_BOOSTER)),
                String.valueOf(getItemPrice(Items.PERCEPTION_BOOSTER))
        );
    }

    /**
     * Сброс остатков предметов
     */
    public void resetStocks() {
        initAssortment();
    }

    public int getItemPrice(Items items) {
        for (Assortment assortment : assortments) {
            if (assortment.getItem().equals(items)) {
                return assortment.getPrice();
            }
        }
        return 0;
    }

    private int getItemStock(Items items) {
        for (Assortment assortment : assortments) {
            if (assortment.getItem().equals(items)) {
                return assortment.getStock();
            }
        }
        return -1;
    }

    private void updateItemStock(Items items, int stock) {
        for (Assortment assortment : assortments) {
            if (assortment.getItem().equals(items)) {
                assortment.setStock(stock);
            }
        }
    }

    private Assortment getAssortment(Items items) {
        for (Assortment assortment : assortments) {
            if (assortment.getItem().equals(items)) {
                return assortment;
            }
        }
        return null;
    }
}

class Assortment {
    private Items item;
    private int stock;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

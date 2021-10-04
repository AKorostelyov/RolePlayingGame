package ru.skillfactory.trades;

import ru.skillfactory.creatures.player.Player;
import ru.skillfactory.game.Configuration;
import ru.skillfactory.game.GameMessages;
import ru.skillfactory.maintenance.Printer;

import java.util.Random;

/**
 * Класс, описывающий работу торговца
 */
public class TradeShop {
    private double healPoisonPrice;
    private int healPoisonStock;
    private double armorPrice;
    private int armorStock;

    public TradeShop() {
        this.healPoisonPrice = 15;
        this.healPoisonStock = Configuration.HEAL_POISON_DEFAULT_STOCK;
        this.armorPrice = 48;
        this.armorStock = Configuration.ARMOR_DEFAULT_STOCK;
    }

    public double getHealPoisonPrice() {
        return healPoisonPrice;
    }

    public void setHealPoisonPrice(double healPoisonPrice) {
        this.healPoisonPrice = healPoisonPrice;
    }

    public double getHealPoisonStock() {
        return healPoisonStock;
    }

    public void setHealPoisonStock(int healPoisonStock) {
        this.healPoisonStock = healPoisonStock;
    }

    public double getArmorPrice() {
        return armorPrice;
    }

    public void setArmorPrice(double armorPrice) {
        this.armorPrice = armorPrice;
    }

    public double getArmorStock() {
        return armorStock;
    }

    public void setArmorStock(int armorStock) {
        this.armorStock = armorStock;
    }

    /**
     * Обновляет цены, изменяя их случайным образом в отрезке (-15, 15) процентов от базовой цены
     */
    public void updatePrices() {
        double inflation = new Random().nextInt(15) * healPoisonPrice / 100;
        healPoisonPrice += Math.round((new Random().nextBoolean()) ? (-1) * inflation : inflation);
        armorPrice += Math.round((new Random().nextBoolean()) ? (-1) * inflation : inflation);
    }

    /**
     * Покупка предметов
     *
     * @param item  желаемый предмет
     * @param count количество
     * @return успешность операции
     */
    public boolean buyItem(Items item, int count) {
        int remainStock = 0;
        switch (item) {
            case HEAL:
                remainStock = trade(
                        count,
                        Player.getInstance().getMoney(),
                        healPoisonPrice,
                        healPoisonStock);
                if (remainStock < 0) {
                    return false;
                } else {
                    healPoisonStock = remainStock;
                    return true;
                }
            case ARMOR:
                remainStock = trade(
                        count,
                        Player.getInstance().getMoney(),
                        armorPrice,
                        armorStock);
                if (remainStock < 0) {
                    return false;
                } else {
                    armorStock = remainStock;
                    return true;
                }
            default:
                Printer.print(GameMessages.TRADER_UNKNOWN_ITEM_MESSAGE);
                return false;
        }
    }

    /**
     * Проведение операции, в зависимости от количества денег у игрока и остатков у торговца
     *
     * @param count запрашиваемое количество предметов
     * @param money количество денег у игрока
     * @param price цена одного предмета
     * @param stock остаток предмета
     * @return новое значение остатков предмета
     */
    private int trade(int count, double money, double price, int stock) {
        if (money < price * count) {
            Printer.print(GameMessages.TRADER_NOT_ENOUGH_MONEY_MESSAGE);
            if (count > 1) {
                Printer.formatPrint(GameMessages.TRADER_AFFORD_ITEMS_COUNT_MESSAGE,
                        String.valueOf(Math.round(money / price))
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
                String.valueOf(healPoisonStock),
                String.valueOf(healPoisonPrice),
                String.valueOf(armorStock),
                String.valueOf(armorPrice)
        );
    }

    /**
     * Сброс остатков предметов
     */
    public void resetStocks() {
        healPoisonStock = Configuration.HEAL_POISON_DEFAULT_STOCK;
        armorStock = Configuration.ARMOR_DEFAULT_STOCK;
    }
}

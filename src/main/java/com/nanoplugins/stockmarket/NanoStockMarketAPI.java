package com.nanoplugins.stockmarket;

public class NanoStockMarketAPI {

    private static NanoStockMarketAPI instance;

    public static NanoStockMarketAPI get() {
        if (instance == null)
            instance = new NanoStockMarketAPI();
        return instance;
    }

    private int stockMarket = 1;

    public void setStockMarket(int stockMarket) {
        this.stockMarket = stockMarket;
    }

    public int getStockMarket() {
        return stockMarket;
    }
}

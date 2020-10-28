package com.nanoplugins.stockmarket.task;

import com.nanoplugins.stockmarket.NanoStockMarketAPI;
import com.nanoplugins.stockmarket.event.NanoStockMarketUpdate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class StockMarketTask extends TimerTask {

    private final NanoStockMarketAPI api;
    private final int min, max, differenceValue;
    private final boolean difference;
    private final List<String> message;

    public StockMarketTask(NanoStockMarketAPI api, FileConfiguration config) {
        this.api = api;
        min = config.getInt("settings.value.min");
        max = config.getInt("settings.value.max");
        differenceValue = config.getInt("settings.difference.value");
        difference = config.getBoolean("settings.difference.use");
        message = config.getStringList("messages.update-message").stream().map(s -> s.replace("&", "§")).collect(Collectors.toList());
    }

    @Override
    public void run() {
        int stockMarket = api.getStockMarket();

        if (difference) {
            int plus = stockMarket + differenceValue;
            int minus = stockMarket - differenceValue;
            api.setStockMarket(ThreadLocalRandom.current().nextBoolean() ? Math.min(plus, max) : Math.max(minus, min));
        } else {
            int a = ThreadLocalRandom.current().nextInt(min, max);
            while (a == stockMarket) {
                a = ThreadLocalRandom.current().nextInt(min, max);
            }
            api.setStockMarket(a);
        }

        int newStockMarket = api.getStockMarket();

        Bukkit.getPluginManager().callEvent(new NanoStockMarketUpdate(stockMarket, newStockMarket));

        for (String line : message)
            Bukkit.broadcastMessage(line
                    .replace("%old%", Integer.toString(stockMarket))
                    .replace("%new%", Integer.toString(newStockMarket)));
    }
}

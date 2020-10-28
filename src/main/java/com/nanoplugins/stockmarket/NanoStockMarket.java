package com.nanoplugins.stockmarket;

import com.nanoplugins.stockmarket.command.StockMarketCommand;
import com.nanoplugins.stockmarket.hook.PlaceholderAPIHook;
import com.nanoplugins.stockmarket.task.StockMarketTask;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Timer;

public class NanoStockMarket extends JavaPlugin {

    private Timer timer;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        timer = new Timer();
        NanoStockMarketAPI api = new NanoStockMarketAPI();
        loadTask(api, getConfig());
        new PlaceholderAPIHook(this, api).register();
        new StockMarketCommand(this, api);
    }

    @Override
    public void onDisable() {
        timer.cancel();
    }

    public void loadTask(NanoStockMarketAPI api, FileConfiguration config) {
        timer.schedule(new StockMarketTask(api, config), 5 * 1000, config.getInt("settings.delay") * 1000);
    }


}

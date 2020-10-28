package com.nanoplugins.stockmarket.hook;

import com.nanoplugins.stockmarket.NanoStockMarket;
import com.nanoplugins.stockmarket.NanoStockMarketAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    private final NanoStockMarket plugin;
    private final NanoStockMarketAPI api;

    public PlaceholderAPIHook(NanoStockMarket plugin, NanoStockMarketAPI api) {
        this.plugin = plugin;
        this.api = api;
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return "Morais";
    }

    @Override
    public String getIdentifier(){
        return "bolsa";
    }

    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player p, String params) {
        return Integer.toString(api.getStockMarket());
    }
}
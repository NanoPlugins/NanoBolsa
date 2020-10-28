package com.nanoplugins.stockmarket.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class NanoStockMarketUpdate extends Event {

    private final int oldValue, newValue;

    public int getOldValue() {
        return oldValue;
    }

    public int getNewValue() {
        return newValue;
    }

    public NanoStockMarketUpdate(int oldValue, int newValue) {
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}

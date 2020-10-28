package com.nanoplugins.stockmarket.command;

import com.nanoplugins.stockmarket.NanoStockMarket;
import com.nanoplugins.stockmarket.NanoStockMarketAPI;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.stream.Collectors;

public class StockMarketCommand implements CommandExecutor {

    private final String check, noPerm, needArgs, mustBeNumber;
    private final List<String> updateMessage;
    private final NanoStockMarketAPI api;

    public StockMarketCommand(NanoStockMarket plugin, NanoStockMarketAPI api) {
        this.api = api;
        plugin.getCommand("bolsa").setExecutor(this);
        mustBeNumber = plugin.getConfig().getString("messages.must-be-number").replace("&", "§");
        needArgs = plugin.getConfig().getString("messages.need-args").replace("&", "§");
        noPerm = plugin.getConfig().getString("messages.no-perm").replace("&", "§");
        check = plugin.getConfig().getString("messages.check").replace("&", "§");
        updateMessage = plugin.getConfig().getStringList("messages.update-message").stream().map(s -> s.replace("&", "§")).collect(Collectors.toList());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(check.replace("%stockMarket%", Integer.toString(api.getStockMarket())));
            return true;
        }

        if (args[0].equalsIgnoreCase("set")) {
            if (!sender.hasPermission("bolsa.admin")) {
                sender.sendMessage(noPerm);
                return true;
            }

            if (args.length < 2) {
                sender.sendMessage(needArgs);
                return true;
            }

            if (!StringUtils.isNumeric(args[1])) {
                sender.sendMessage(mustBeNumber);
                return true;
            }

            int stockMarket = api.getStockMarket();
            int newStockMarket = Integer.parseInt(args[1]);
            api.setStockMarket(newStockMarket);

            for (String line : updateMessage) Bukkit.broadcastMessage(line
                        .replace("%oldStockMarket%", Integer.toString(stockMarket))
                        .replace("%stockMarket%", Integer.toString(newStockMarket)));

        }

        return false;
    }
}

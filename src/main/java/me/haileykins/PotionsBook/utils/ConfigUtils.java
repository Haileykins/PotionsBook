package me.haileykins.PotionsBook.utils;

import me.haileykins.PotionsBook.Pbook;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigUtils {

    private Pbook plugin;

    public boolean updaterEnabled;
    public double bookFee;
    public String pluginOutOfDate;
    public String noPermission;
    public String mustBePlayer;
    public String cfgReloaded;
    public String unknownCmd;
    public String prefix;
    public String purchaseMessage;
    public String fullInvMessage;
    public String notEnoughMoneyMsg;
    String bookAuthor;
    String bookTitle;

    public ConfigUtils(Pbook pl) {
        plugin = pl;
    }

    public String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        bookFee = config.getDouble("Book-Fee");
        bookAuthor = config.getString("Book-Author");
        bookTitle = config.getString("Book-Title");
        prefix = config.getString("Prefix");
        purchaseMessage = config.getString("Purchase-Msg");
        fullInvMessage = config.getString("Full-Inventory-Msg");
        notEnoughMoneyMsg = config.getString("Not-Enough-Money-Msg");
        noPermission = config.getString("No-Permission-Msg");
        mustBePlayer = config.getString("Must-Be-Player-Msg");
        cfgReloaded = config.getString("Config-Reloaded-Msg");
        unknownCmd = config.getString("Unknown-Command");

    }

    public void reloadConfig() {
        plugin.reloadConfig();
        loadConfig();
        plugin.getConfig();
        plugin.saveConfig();
    }
}

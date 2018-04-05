package me.haileykins.PotionsBook;

import me.haileykins.PotionsBook.commands.CommandPotionBook;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Pbook extends JavaPlugin {
    private Economy econ;

    private boolean useEconomy = true;
    private double bookFee = 100.0;

    public Pbook() {
    }

    @Override
    public void onEnable() {
        // Load Config
        SetConfig();

        // Look for Vault
        setupEconomy();

        if (econ == null)
            getLogger().warning("Vault Not Detected, Plugin Economy Use Disabled");

        // Register Commands
        getCommand("pbook").setExecutor(new CommandPotionBook(this));

        getLogger().info("Is Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Is Disabled");
    }

    private void SetConfig() {
        FileConfiguration config = getConfig();
        useEconomy = config.getBoolean("UseEconomy", useEconomy);
        bookFee = config.getDouble("BookFee", bookFee);
        // write in case they're missing
        config.set("UseEconomy", useEconomy);
        config.set("BookFee", bookFee);
        saveConfig();
    }

    private void setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null)
            econ = rsp.getProvider();
    }

    public boolean hasMoney(OfflinePlayer player, double amount) {
        if (! useEconomy)
            return true;

        return econ.has(player, amount);
    }


    public boolean takeMoney(OfflinePlayer player, double amount) {
        if (! useEconomy)
            return true;

        if (econ.has(player, amount)) {
            econ.withdrawPlayer(player, amount);
            return true;
        }

        return false;
    }

    public double getBookFee() {
        return bookFee;
    }
}

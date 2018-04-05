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
    private String bookAuthor = "Console";
    private String bookTitle = "Potion Brewing Guide";
    private String purchaseMessage = "You purchased a potion book for: $";
    private String fullInvMessage = "The book wouldn't fit in your inventory";
    private String notEnoughMoneyMsg = "You can't afford a PotionBook for: $";

    public Pbook() {
    }

    @Override
    public void onEnable() {
        // Load Config
        SetConfig();

        // Look for Vault
        setupEconomy();

        if (!setupEconomy() ) {
            getLogger().severe("Disabled: Vault Not Found");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

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
        useEconomy = config.getBoolean("Use-Economy", useEconomy);
        bookFee = config.getDouble("Book-Fee", bookFee);
        bookAuthor = config.getString("Book-Author", bookAuthor);
        bookTitle = config.getString("Book-Title", bookTitle);
        purchaseMessage = config.getString("Purchase-Message", purchaseMessage);
        fullInvMessage = config.getString("Full-Inventory-Message", fullInvMessage);
        notEnoughMoneyMsg = config.getString("Not-Enough-Money-Message", notEnoughMoneyMsg);
        // write in case they're missing
        config.set("Use-Economy", useEconomy);
        config.set("Book-Fee", bookFee);
        config.set("Book-Author", bookAuthor);
        config.set("Book-Title", bookTitle);
        config.set("Purchase-Message", purchaseMessage);
        config.set("Full-Inventory-Message", fullInvMessage);
        config.set("Not-Enough-Money-Message", notEnoughMoneyMsg);
        saveConfig();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
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

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getPurchaseMessage() {
        return purchaseMessage;
    }

    public String getFullInvMessage() {
        return fullInvMessage;
    }

    public String getNotEnoughMoneyMsg() {
        return notEnoughMoneyMsg;
    }
}

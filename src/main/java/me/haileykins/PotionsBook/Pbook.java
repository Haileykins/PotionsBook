package me.haileykins.PotionsBook;

import me.haileykins.PotionsBook.commands.CommandPB;
import me.haileykins.PotionsBook.listeners.UpdateListener;
import me.haileykins.PotionsBook.utils.BookUtils;
import me.haileykins.PotionsBook.utils.ConfigUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Pbook extends JavaPlugin {

    public Economy economy;

    @Override
    public void onEnable() {

        // Create Instances
        ConfigUtils cfgUtils = new ConfigUtils(this);
        BookUtils bookUtils = new BookUtils(cfgUtils, this);

        // Check For Vault
        if (!checkEconomy()) {
            getServer().getPluginManager().disablePlugin(this);
        }

        // Register Listeners
        getServer().getPluginManager().registerEvents(new UpdateListener(cfgUtils, this), this);

        // Register Commands
        getCommand("pbook").setExecutor(new CommandPB(this, bookUtils, cfgUtils));

        // Load Config
        cfgUtils.loadConfig();
    }

    private boolean checkEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }
}

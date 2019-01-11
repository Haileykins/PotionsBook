package me.haileykins.PotionsBook.utils;

import me.haileykins.PotionsBook.Pbook;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class BookUtils {

    private ConfigUtils cfgUtils;
    private Pbook plugin;

    public BookUtils(ConfigUtils configUtils, Pbook pl) {
        cfgUtils = configUtils;
        plugin = pl;
    }

    // Create The Book
    public ItemStack book() {
        ItemStack potionbook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bm = (BookMeta) potionbook.getItemMeta();
        bm.addPage("Night Vision:\n\n3 Minutes:\nNether Wart + Gold Carrot\n\n8 Minutes:"
                + "\nAdd Redstone Dust");
        bm.addPage("Invisibility:\n\n3 Minutes:\nNether Wart + Golden Carrot + Fermented Spider Eye\n\n8 "
                + "Minutes:\nAdd Redstone Dust");
        bm.addPage("Fire Resistance:\n\n3 Minutes:\nNether Wart + Magma Cream\n\n8 Minutes:\n"
                + "Add Redstone Dust");
        bm.addPage("Leaping:\n\n3 Minutes:\nNether Wart + Rabbits Foot\n\n1.5 Minutes:\n"
                + "Add Glowstone Dust\n\n8 Minutes:\nAdd Redstone Dust");
        bm.addPage("Slowness:\n\n1.5 Minutes:\nNether Wart + Rabbits Foot + Fermented Spider Eye OR Nether Wart" +
                " + Sugar + Fermented Spider Eye" + "\n\n4 Minutes:\nAdd Redstone Dust");
        bm.addPage("Swiftness:\n\n3 Minutes:\nNether Wart + Sugar\n\n1.5 Minutes:\n"
                + "Add Glowstone Dust\n\n8 Minutes:\nAdd Redstone Dust");
        bm.addPage("Water Breathing:\n\n3 Minutes:\nNether Wart + Pufferfish\n\n8 Minutes:"
                + "\nAdd Redstone");
        bm.addPage("Healing:\n\nHealing 1:\nNether Wart + Glistening Melon\n\nHealing 2:\n"
                + "Add Glowstone Dust");
        bm.addPage("Harming:\n\nHarming 2:\nNether Wart + Glistening Melon + Fermented Spider Eye OR Nether " +
                "Wart + Spider Eye = Fermented Spider Eye" + "\n\nHarming 2:\nAdd Glowstone Dust");
        bm.addPage("Poison:\n\n45 Seconds:\nNether Wart + Spider Eye\n\n21 Seconds:\nAdd"
                + " Glowstone Dust\n\n1.5 Minutes:\nAdd Redstone Dust");
        bm.addPage("Regeneration:\n\n45 Seconds:\nNether Wart + Ghast Tear\n\n21 Seconds:\nAdd"
                + " Glowstone Dust\n\n1.5 Minutes:\nAdd Redstone Dust");
        bm.addPage("Strength:\n\n3 Minutes:\nNether Wart + Blaze Powder\n\n1.5 Minutes: Add"
                + " Glowstone Dust\n\n8 Minutes:\nAdd Redstone Dust");
        bm.addPage("Weakness:\n\n1.5 Minutes:\nFermented Spider Eye\n\n4 Minutes:\nAdd Redstone");

        if (plugin.getServer().getVersion().contains("1.13")) {
            bm.addPage("Turtle Master:\n\n20 Seconds:\nNether Wart + Turtle Shell\n\n40 Seconds:\nAdd Redstone" +
                    "\n\nTurtle Master 2:\n20 Seconds\nAdd Glowstone");
            bm.addPage("Slow Falling:\n\n1.5 Minutes:\nNether Wart + Phantom Membrane\n\n4 Minutes:\nAdd Redstone");
        }

        bm.setAuthor(cfgUtils.bookAuthor);
        bm.setTitle(cfgUtils.bookTitle);
        potionbook.setItemMeta(bm);
        return potionbook;
    }

    public boolean hasMoney(Player player, double amount) {
        return plugin.economy.has(player, amount);
    }


    @SuppressWarnings("UnusedReturnValue")
    public boolean takeMoney(Player player, double amount) {
        if (plugin.economy.has(player, amount)) {
            plugin.economy.withdrawPlayer(player, amount);
            return true;
        }

        return false;
    }
}

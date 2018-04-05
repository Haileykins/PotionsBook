package me.haileykins.PotionsBook.commands;

import me.haileykins.PotionsBook.Pbook;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Map;

public class CommandPotionBook implements CommandExecutor {

    private Pbook plugin;

    public CommandPotionBook(Pbook pl) {
        plugin = pl;
    }

    // Create The Book
    private ItemStack PotionBook() {
        ItemStack potionbook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bm = (BookMeta) potionbook.getItemMeta();
        bm.addPage("Night Vision:\n\n3 Minutes:\nNether Wart + Gold Carrot\n\n8 Minutes:"
                + "\nAdd Redstone Dust");
        bm.addPage("Invisibility:\n\n3 Minutes:\nNether Wart + Fermented Spider Eye\n\n8 "
                + "Minutes:\nAdd Redstone Dust");
        bm.addPage("Fire Resistance:\n\n3 Minutes:\nNether Wart + Magma Cream\n\n8 Minutes:\n"
                + "Add Redstone Dust");
        bm.addPage("Leaping:\n\n3 Minutes:\nNether Wart + Rabbits Foot\n\n1.5 Minutes:\n"
                + "Add Glowstone Dust\n\n8 Minutes:\nAdd Redstone Dust");
        bm.addPage("Slowness:\n\n1.5 Minutes:\nNether Wart + Rabbits Foot + Fermented Spider Eye"
                + "\n\n4 Minutes:\nAdd Redstone Dust");
        bm.addPage("Swiftness:\n\n3 Minutes:\nNether Wart + Sugar\n\n1.5 Minutes:\n"
                + "Add Glowstone Dust\n\n8 Minutes:\nAdd Redstone Dust");
        bm.addPage("Water Breathing:\n\n3 Minutes:\nNether Wart + Pufferfish\n\n8 Minutes:"
                + "\nAdd Redstone");
        bm.addPage("Healing:\n\nHealing 1:\nNether Wart + Glistening Melon\n\nHealing 2:\n"
                + "Add Glowstone Dust");
        bm.addPage("Harming:\n\nHarming 2:\nNether Wart + Glistening Melon + Fermented Spider Eye"
                + "\n\nHarming 2:\nAdd Glowstone Dust");
        bm.addPage("Poison:\n\n45 Seconds:\nNether Wart + Spider Eye\n\n21 Seconds:\nAdd"
                + " Glowstone Dust\n\n1.5 Minutes:\nAdd Redstone Dust");
        bm.addPage("Regeneration:\n\n45 Seconds:\nNether Wart + Ghast Tear\n\n21 Seconds:\nAdd"
                + " Glowstone Dust\n\n1.5 Minutes:\nAdd Redstone Dust");
        bm.addPage("Strength:\n\n3 Minutes:\nNether Wart + Blaze Powder\n\n1.5 Minutes: Add"
                + " Glowstone Dust\n\n8 Minutes:\nAdd Redstone Dust");
        bm.addPage("Weakness:\n\n1.5 Minutes:\nFermented Spider Eye\n\n4 Minutes:\nAdd Redstone");
        bm.setAuthor(plugin.getBookAuthor());
        bm.setTitle(plugin.getBookTitle());
        potionbook.setItemMeta(bm);
        return potionbook;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this!");
            return true;
        }

        Player player = (Player) sender;

        if (plugin.hasMoney(player, plugin.getBookFee())) {

            Map<Integer, ItemStack> nofit = player.getInventory().addItem(PotionBook());

            if (nofit.isEmpty()) {
                plugin.takeMoney(player, plugin.getBookFee());
                sender.sendMessage(plugin.getPurchaseMessage() + plugin.getBookFee());
            } else {
                sender.sendMessage(plugin.getFullInvMessage());
                return true;
            }

        } else {
            sender.sendMessage(plugin.getNotEnoughMoneyMsg());
            return true;
        }

        return true;
    }
}

package me.haileykins.PotionsBook.commands;

import me.haileykins.PotionsBook.utils.BookUtils;
import me.haileykins.PotionsBook.utils.ConfigUtils;
import me.haileykins.PotionsBook.Pbook;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class CommandPB implements CommandExecutor {

    private Pbook plugin;
    private BookUtils bkUtils;
    private ConfigUtils cfgUtils;

    public CommandPB(Pbook pl, BookUtils bookUtils, ConfigUtils configUtils) {
        plugin = pl;
        bkUtils = bookUtils;
        cfgUtils = configUtils;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("pbook.admin")) {
                    cfgUtils.reloadConfig();
                    sender.sendMessage(cfgUtils.colorize(cfgUtils.prefix + " " + cfgUtils.cfgReloaded));
                    plugin.getLogger().info(cfgUtils.cfgReloaded);
                    return true;
                }
                sender.sendMessage(cfgUtils.colorize(cfgUtils.prefix + " " + cfgUtils.noPermission));
                return true;
            }
            sender.sendMessage(cfgUtils.colorize(cfgUtils.prefix + " " + cfgUtils.unknownCmd));
            return true;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (bkUtils.hasMoney(player, cfgUtils.bookFee)) {

                Map<Integer, ItemStack> nofit = player.getInventory().addItem(bkUtils.book());

                if (nofit.isEmpty()) {
                    bkUtils.takeMoney(player, cfgUtils.bookFee);
                    player.sendMessage(cfgUtils.colorize(cfgUtils.prefix + " " + cfgUtils.purchaseMessage
                            .replace("{price}", Double.toString(cfgUtils.bookFee))));
                    return true;

                }
                player.sendMessage(cfgUtils.colorize(cfgUtils.prefix + " " + cfgUtils.fullInvMessage));
                return true;

            }
            player.sendMessage(cfgUtils.colorize(cfgUtils.prefix + " " + cfgUtils.notEnoughMoneyMsg
                    .replace("{price}", Double.toString(cfgUtils.bookFee))));
            return true;

        }
        sender.sendMessage(cfgUtils.colorize(cfgUtils.prefix + " " + cfgUtils.mustBePlayer));
        return true;

    }
}

package me.haileykins.PotionsBook.listeners;

import com.avaje.ebean.Update;
import me.haileykins.PotionsBook.Pbook;
import me.haileykins.PotionsBook.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateListener implements Listener {

    private Pbook plugin;
    private ConfigUtils cfgUtils;

    private final String resourceURL = "https://api.spigotmc.org/legacy/update.php?resource=59045";

    public UpdateListener(ConfigUtils configUtils, Pbook pl) {
        cfgUtils = configUtils;
        plugin = pl;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!cfgUtils.updaterEnabled) {
            return;
        }

        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                HttpsURLConnection connection = (HttpsURLConnection) new URL(resourceURL).openConnection();
                String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

                if (!plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                    player.sendMessage(cfgUtils.colorize(cfgUtils.prefix + " " + cfgUtils.pluginOutOfDate));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

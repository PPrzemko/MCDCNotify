package org.thw.dcnotify;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerTimeTracker implements Listener {
    private final HashMap<UUID, Long> joinTimes = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(PlayerJoinListener.class.getName());

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        joinTimes.put(player.getUniqueId(), System.currentTimeMillis());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Long joinTime = joinTimes.get(player.getUniqueId());

        if (joinTime != null) {
            long onlineTime = System.currentTimeMillis() - joinTime;
            System.out.println(player.getName() + " was online for " + onlineTime + " milliseconds.");

            DiscordWebhook k = new DiscordWebhook("https://discord.com/api/webhooks//");
            int onlinePlayerCount = Bukkit.getOnlinePlayers().size();
            k.setContent(event.getPlayer().getName() + " ist von dem Server gegangen! Er war " + onlineTime / 1000 / 60 + " Minuten online. Es sind nun " + onlinePlayerCount + " Spieler online.");

            try {
                k.execute();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An error occurred while executing the webhook", e);
            }


        }
    }
}
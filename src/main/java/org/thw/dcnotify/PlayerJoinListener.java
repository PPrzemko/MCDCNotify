package org.thw.dcnotify;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.broadcast;
import static org.bukkit.Bukkit.getServer;

public class PlayerJoinListener implements Listener {
    private static final Logger LOGGER = Logger.getLogger(PlayerJoinListener.class.getName());

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        DiscordWebhook k = new DiscordWebhook("https://discord.com/api/webhooks//");
        int onlinePlayerCount=Bukkit.getOnlinePlayers().size();
        k.setContent(event.getPlayer().getName() + " ist dem Server beigetreten! Es sind nun "+onlinePlayerCount+" Spieler online.");

        try {
            k.execute();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while executing the webhook", e);
        }
        broadcast(Component.text("Player " + event.getPlayer().getName() + " has joined the server!"));
    }
}

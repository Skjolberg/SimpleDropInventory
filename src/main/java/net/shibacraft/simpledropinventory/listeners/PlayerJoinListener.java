package net.shibacraft.simpledropinventory.listeners;

import net.shibacraft.simpledropinventory.commands.MainCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Set;
import java.util.UUID;


public class PlayerJoinListener implements Listener {

    private final Set<UUID> drop = MainCommand.getDrop();

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void playerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        if (p.hasPermission("sdi.use")) {
            drop.add(p.getUniqueId());
        }

    }

}

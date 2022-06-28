package net.shibacraft.simpledropinventory.listeners;

import de.leonhard.storage.Yaml;
import net.shibacraft.simpledropinventory.files.FileManager;
import net.shibacraft.simpledropinventory.module.EventsModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Set;
import java.util.UUID;


public class PlayerJoinListener implements Listener {

    Yaml config = FileManager.getFilesYaml().get("Config");
    public static final Set<UUID> drop = EventsModule.getDrop();

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerJoin(PlayerJoinEvent event) {

        Player p = event.getPlayer();

        if(config.getBoolean("PlayerJoinDrop")){
            if(p.hasPermission("sdi.use")){
                drop.add(p.getUniqueId());
            }
        }

    }
}

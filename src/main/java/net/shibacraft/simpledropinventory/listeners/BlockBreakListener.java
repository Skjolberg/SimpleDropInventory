package net.shibacraft.simpledropinventory.listeners;

import de.leonhard.storage.Yaml;
import net.shibacraft.simpledropinventory.commands.MainCommand;
import net.shibacraft.simpledropinventory.files.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Set;
import java.util.UUID;

public class BlockBreakListener implements Listener {

    private final Set<UUID> drop = MainCommand.getDrop();
    private final Yaml config = FileManager.getFilesYaml().get("Config");

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void blockDropItem(BlockBreakEvent event) {
        String world = event.getPlayer().getWorld().getName();
        if (UtilsListener.isWorldDisabled(world)) return;

        if (!UtilsListener.isCollectExperience()) return;

        Player p = event.getPlayer();

        if (drop.contains(p.getUniqueId()) || config.getBoolean("Always-enabled") && p.hasPermission("sdi.use")) {
            int exp = event.getExpToDrop();
            event.getPlayer().giveExp(exp);
            event.setExpToDrop(0);
        }

    }

}
